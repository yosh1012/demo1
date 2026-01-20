package demo1.taskmanagement.lib.redis.connector

// dependencies
import com.typesafe.config.ConfigFactory
import io.lettuce.core.{RedisClient, RedisURI, RedisConnectionException}
import io.lettuce.core.api.StatefulRedisConnection
import com.typesafe.scalalogging.LazyLogging

// singleton
object RedisConnector extends LazyLogging {
    private val config = ConfigFactory.load()
    private val redisHost: String = config.getString("redis.host")
    private val redisPort: Int = config.getInt("redis.port")

    private val redisUri: RedisURI = RedisURI.Builder
        .redis(redisHost, redisPort)
        .build()
    
    private val redisClient: RedisClient = RedisClient.create(redisUri)

    private lazy val redisConnection: StatefulRedisConnection[String, String] = {
        // Lettuce RedisClient connect() returns java exception so use try-catch
        try {
            // logger.debug(s"Getting Redis connection to $redisHost: $redisPort")
            redisClient.connect()
        } catch {
            case e: RedisConnectionException =>
                logger.error(s"Exception: failed to connect to Redis at $redisHost:$redisPort", e)
                throw e
            case e: Exception =>
                logger.error(s"Exception: unexpected error during Redis connection", e)
                throw e
        }

    }

    /**
     * get Redis connection
     * @return current connection
     */
    def getConnection(): StatefulRedisConnection[String, String] = {
        // logger.debug(s"Getting Redis connection to $redisHost: $redisPort")
        redisConnection
    }

    /**
     * close connection
     */
    def closeConnection(): Unit = {
        if (redisClient != null) {
            // logger.debug(s"Closing Redis Client")
            redisClient.shutdown()
        }
    }
}