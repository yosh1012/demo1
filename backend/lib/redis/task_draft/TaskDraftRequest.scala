package demo1.taskmanagement.lib.redis.task_draft

// dependencies
import play.api.libs.json._

final case class TaskDraftRequest(
    tsk_id: Long,
    usr_id: Long,
    content_update: String
)

// companion for json formatting
object TaskDraftRequest {
    implicit val format: Format[TaskDraftRequest] = Json.format[TaskDraftRequest]
}
