package demo1.taskmanagement.lib.tasks

// dependencies
import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}
import com.typesafe.scalalogging.LazyLogging
import demo1.taskmanagement.lib.postgres.tasks.Task

final case class TaskCreateRequest(
    tsk_name: String,
    tsk_description: Option[String],
    tsk_start_date: Option[LocalDate],
    tsk_due_date: Option[LocalDate]
)

object TaskCreateRequest extends LazyLogging {
    implicit val format: OFormat[TaskCreateRequest] = Json.format[TaskCreateRequest]

    /**
     * create Task model from TaskCreateRequest
     * @param taskCreateRequestData
     * @param prj_id
     * @return Task model
     */
    def createModel(taskCreateRequestData: TaskCreateRequest, prj_id: Long): Task = {
        val currentTime: LocalDateTime = LocalDateTime.now()
        Task(
            tsk_id = 0L, // will be replaced with the number from DB auto-increment
            tsk_name = taskCreateRequestData.tsk_name,
            tsk_description = taskCreateRequestData.tsk_description,
            tsk_description_version = 1L,
            tsk_start_date = taskCreateRequestData.tsk_start_date,
            tsk_due_date = taskCreateRequestData.tsk_due_date,
            tsk_estimated_hours = None,
            tsk_status = "todo",
            tsk_created_at = currentTime,
            tsk_updated_at = None,
            tsk_deleted_at = None,
            prj_id = prj_id,
            spr_id = None
        )
    }
}