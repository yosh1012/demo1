package com.taskmanagement.lib.redis.task_draft.demo1

// dependencies
import scala.concurrent.{ExecutionContext, Future}
import com.typesafe.scalalogging.LazyLogging
import com.taskmanagement.lib.redis.demo1.TaskDraftRedisRepo

class TaskDraftService(repo: TaskDraftRedisRepo)
    (implicit executionContext: ExecutionContext) extends LazyLogging {
    
    
}