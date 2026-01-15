package com.taskmanagement.lib.redis.task_draft.demo1

// dependencies
import play.api.libs.json._

case class TaskDraftRequest(
    tsk_id: Long,
    usr_id: Long,
    content_update: String
)

// companion for json formatting
object TaskDraftRequest {
    implicit val format: Format[TaskDraftRequest] = Json.format[TaskDraftRequest]
}
