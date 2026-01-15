package com.taskmanagement.lib.redis.task_draft.demo1

// dependencies
import com.typesafe.ConfigFactory
import io.lettuce.core.api.async.{RedisAsyncCommands, RedisStringAsyncCommands}
import io.lettuce.core.{RedisFuture}
import io.lettuce.core.api.StatefulRedisConnection
import scala.concurrent.{Future, ExecutionContext, Promise}
import scala.util.{Success, Failure}
import com.typesafe.scalalogging.LazyLogging

class TaskDraftRedisRepo(redisConnection: StatefulRedisConnection[String, String])
    (implicit executionContext: ExecutionContext) extends LazyLogging {

    private val config = ConfigFactory.load()
    private val prefix = config.getString("redis.task_draft_prefix")
    private val ttl = config.getInt("redis.task_draft_ttl")
    
    private val asyncStringCommands: RedisStringAsyncCommands[String, String] = redisConnection.async()
    private val asyncCommands: RedisAsyncCommands[String,String] = redisConnection.async()
    
    /**
     * convert RedisFuture into Scala's Future
     * @param redisFuture
     * @return 
     */
     // lettuce returns String(by setex/get) or Long(by del)
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
    private def makeKey(tsk_id: Long): String = s"$prefix:$tsk_id"

    /**
     * save draft
     * @param tsk_id
     * @param content
     * @return retuen success Future[] if the save command succeeds
     */
    def save(tsk_id: Long, content: String): Future[Unit] = {
        val key = makeKey(tsk_id)
        val redisFuture: RedisFuture[String] = asyncStringCommands.setex(key, ttl, content)
        // throw away the return result 
        convertToScalaFuture(redisFuture).map(_=>())
    }

    /**
     * get current draft
     * @param tsk_id
     * @return current draft text content
     */
    def get(tsk_id: Long): Future[Option[String]] = {
        val key = makeKey(tsk_id)
        val redisFuture: RedisFuture[String] = asyncStringCommands.get(key)
        convertToScalaFuture(redisFuture).map(Option(_))
    }

    /**
     * delete draft
     * @param tsk_id
     * @return result of deletion
     */
    def delete(tsk_id: Long): Future[Boolean] = {
        val key = makeKey(tsk_id)
        // lettuce client returns java.lang.Long because it's originally a java lib
        val redisFuture: RedisFuture[java.lang.Long] = asyncCommands.del(key)
        // check the result of deletion. if it's 1, return true
        convertToScalaFuture(redisFuture).map(_ > 0)
    }
    

}