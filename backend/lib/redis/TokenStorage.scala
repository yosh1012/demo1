package com.taskmanagement.lib.redis.demo1

// dependencies
import com.typesafe.ConfigFactory
import io.lettuce.core.api.async.{RedisAsyncCommands, RedisStringAsyncCommands}
import io.lettuce.core.{RedisFuture}
import io.lettuce.core.api.StatefulRedisConnection
import scala.concurrent.{Future, ExecutionContext, Promise}
import scala.util.{Success, Failure}
import com.typesafe.scalalogging.LazyLogging

class TokenStorage(redisConnection: StatefulRedisConnection[String, String])
    (implicit executionContext: ExecutionContext) extends LazyLogging {
    
    private val asyncStringCommands: RedisStringAsyncCommands[String, String] = redisConnection.async()
    private val asyncCommands: RedisAsyncCommands[String,String] = redisConnection.async()
    
    /**
     *
     *
     *
     */
    

}