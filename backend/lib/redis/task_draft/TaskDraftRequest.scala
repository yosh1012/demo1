package com.taskmanagement.lib.redis.task_draft.demo1

// dependencies
import play.api.libs.json._

case class TaskDraftRequest(tsk_id: Long, content: String)

// singleton
object TaskDraftRequest {
    implicit val format: Format[TaskEditMessage] = Json.format[TaskEditMessage]
}
