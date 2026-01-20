package demo1.taskmanagement.lib.postgres.tasks

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

final case class TaskDTO (
        tsk_id: Long, // PK

        tsk_name: String,
        tsk_description: Option[String],
        tsk_description_version: Long, // DEFAULT 1
        tsk_start_date: Option[LocalDate],
        tsk_due_date: Option[LocalDate],
        tsk_estimated_hours: Option[BigDecimal],
        tsk_status: String, // ENUM: TASK_STATUS DEFAULT 'todo'

        tsk_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP
        tsk_updated_at: Option[LocalDateTime],
        tsk_deleted_at: Option[LocalDateTime],

        prj_id: Long, // REFERENCES projects(prj_id) ON DELETE CASCADE

        spr_id: Option[Long] // REFERENCES sprints(spr_id) ON DELETE CASCADE
)

object TaskDTO {
    implicit val format: OFormat[TaskDTO] = Json.format[TaskDTO]

    /**
     * create task DTO entity
     * @param task
     * @return task DTO entity
     */
    def create(task: Task): TaskDTO = {
        TaskDTO(
            tsk_id = task.tsk_id,
            tsk_name = task.tsk_name,
            tsk_description = task.tsk_description,
            tsk_description_version = task.tsk_description_version,
            tsk_start_date = task.tsk_start_date,
            tsk_due_date = task.tsk_due_date,
            tsk_estimated_hours = task.tsk_estimated_hours,
            tsk_status = task.tsk_status,
            tsk_created_at = task.tsk_created_at,
            tsk_updated_at = task.tsk_updated_at,
            tsk_deleted_at = task.tsk_deleted_at,
            prj_id = task.prj_id,
            spr_id = task.spr_id
        )
    }
}