package demo1.taskmanagement.lib.redis.task_draft

// dependencies
import com.typesafe.config.ConfigFactory
import io.lettuce.core.RedisFuture
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.async.{RedisAsyncCommands, RedisStringAsyncCommands}
import scala.concurrent.{Future, ExecutionContext, Promise}
import scala.jdk.CollectionConverters
import scala.util.{Success, Failure}
import com.typesafe.scalalogging.LazyLogging

class TaskDraftRedisRepo(redisConnection: StatefulRedisConnection[String, String])
    (implicit executionContext: ExecutionContext) extends LazyLogging {

    private val config = ConfigFactory.load()
    private val draftRedisKeyPrefix = config.getString("redis.task_draft_prefix")
    private val draftTtlSeconds = config.getLong("redis.task_draft_ttl")
    
    private val asyncCommands: RedisAsyncCommands[String,String] = redisConnection.async()
    
    /**
     * convert RedisFuture into Scala's Future
     * @param redisFuture
     * @return scala's Future[Long or String] *lettuce returns String or Long
     */
    private def convertToScalaFuture[T](redisFuture: RedisFuture[T]): Future[T] = {
        val promise = Promise[T]()
        redisFuture.whenComplete { (result, exception) =>
            if (exception != null) {
                promise.failure(exception)
            } else {
                promise.success(result)
            }
        }
        promise.future
    }

    /**
     * build redis key
     * @param tsk_id
     * @return redis key name
     */
    private def makeDraftRedisKey(tsk_id: Long): String = s"$prefix:$tsk_id"

    /**
     * build channel name for pub(publish) / sub(subscribe)
     * @param tsk_id
     * @return channel name
     */
    private def makeDraftRedisChannelName(tsk_id: Long): String = s"$makeDraftRedisKey:notification:$tsk_id"

    /**
     * save draft
     * @param tsk_id
     * @param updateDataContent
     * @return retuen success Future[] if the save command succeeds
     */
    def pushUpdate(tsk_id: Long, updateDataContent: String): Future[Unit] = {
        val redisKeyData = makeDraftRedisKey(tsk_id)
        val rpushRedisFuture: RedisFuture[java.lang.Long] = redisAsyncCommands.rpush(redisKeyData, updateDataContent)
        val rpushScalaFuture: Future[java.lang.Long] = convertToScalaFuture(rpushRedisFuture)

        val setTtlRedisFuture: RedisFuture[java.lang.Boolean] = asyncCommands.expire(redisKeyData, draftTtlSeconds)
        val setTtlScalaFuture: Future[java.lang.Boolean] = convertToScalaFuture(setTtlRedisFuture)

        rpushScalaFuture.zip(setTtlScalaFuture).map {
            case (_: java.lang.Long, ttlSetSuccess: java.lang.Boolean) => ()
        } 
    }

    /**
     * get all updates
     * @param tsk_id
     * @return current draft text content
     */
    def getAllUpdate(tsk_id: Long): Future[Option[String]] = {
        val redisKeyData = makeDraftRedisKey(tsk_id)

        // lettuce command "lrange" means List range (getting a list by range)
        val lrangeRedisFuture: RedisFuture[java.util.List[String]] = asyncCommands.lrange(redisKeyData, 0, -1)
        convertToScalaFuture(lrangeRedisFuture).map {
            draftUpdateDataJavaList: java.util.List[String] => CollectionConverters.asScala(draftUpdateDataJavaList).toSeq
        }
    }

    /**
     * publish draft update redis notification
     * @param tsk_id
     * @param messageJson
     * @return result of deletion
     */
    def publishUpdate(tsk_id: Long, messageJson: String): Future[Long] = {
        val channelName: String = makeDraftRedisChannelName(tsk_id)
        val publishRedisFuture: RedisFuture[java.lang.Long] = asyncCommands.publish(channelName, messageJson)

        convertToScalaFuture(publishRedisFuture).map {
            numberOfChannelSubscribers: java.lang.Long => numberOfChannelSubscribers.longValue()
        }
    }

    /**
     * delete draft
     * @param tsk_id
     * @return result of deletion
     */
    def deleteDraft(tsk_id: Long): Future[Boolean] = {
        val redisKeyData = makeDraftRedisKey(tsk_id)
        // lettuce client returns java.lang.Long because it's originally a java lib
        val deleteKeyRedisFuture: RedisFuture[java.lang.Long] = asyncCommands.del(redisKeyData)
        // check the result of deletion. if it's 1, return true
        convertToScalaFuture(deleteKeyRedisFuture).map {
            deletedKeyCount: java.lang.Long => deletedKeyCount.longValue() > 0
        }
    }
}