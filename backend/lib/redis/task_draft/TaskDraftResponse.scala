package com.taskmanagement.lib.redis.task_draft.demo1

// dependencies
import play.api.libs.json._

case class TaskDraftResponse(
    tsk_id: Long,
    content_update: Seq[String]
)

// companion for json formatting
object TaskDraftResponse {
    implicit val format: Format[TaskDraftResponse] = Json.format[TaskDraftResponse]
}
