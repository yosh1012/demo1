package demo1.taskmanagement.lib.tasks

// dependencies
import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}
import scala.math.BigDecimal
import com.typesafe.scalalogging.LazyLogging

import demo1.taskmanagement.lib.postgres.tasks.Task
import demo1.taskmanagement.lib.hashids.HashidsHandler

final case class TaskResponse(
    tsk_id: String,
    tsk_name: String,
    tsk_description: Option[String],
    tsk_description_version: Long,
    tsk_start_date: Option[LocalDate],
    tsk_due_date: Option[LocalDate],
    tsk_estimated_hours: Option[BigDecimal],
    tsk_status: String,
    tsk_created_at: LocalDateTime,
    tsk_updated_at: Option[LocalDateTime],
    prj_id: String,
    spr_id: Option[String]
)

object TaskResponse extends LazyLogging {
    implicit val format: OFormat[TaskResponse] = Json.format[TaskResponse]

    /**
     * create TaskResponse from Task model
     * @param taskModel
     * @return task response with hashed ids
     */
    def createTaskResponse(taskModel: Task): TaskResponse = {
        TaskResponse(
            tsk_id = HashidsHandler.encode(taskModel.tsk_id),
            tsk_name = taskModel.tsk_name,
            tsk_description = taskModel.tsk_description,
            tsk_description_version = taskModel.tsk_description_version,
            tsk_start_date = taskModel.tsk_start_date,
            tsk_due_date = taskModel.tsk_due_date,
            tsk_estimated_hours = taskModel.tsk_estimated_hours,
            tsk_status = taskModel.tsk_status,
            tsk_created_at = taskModel.tsk_created_at,
            tsk_updated_at = taskModel.tsk_updated_at,
            prj_id = HashidsHandler.encode(taskModel.prj_id),
            spr_id = taskModel.spr_id.map { (spr_id: Long) => HashidsHandler.encode(spr_id)}
        )
    }
}