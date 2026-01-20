package demo1.taskmanagement.lib.postgres.tasks

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

final case class TaskCategoryDTO (
        tsc_id: Long, // PK

        tsc_name: String,

        tsc_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP,
        tsc_updated_at: Option[LocalDateTime],
        tsc_deleted_at: Option[LocalDateTime],

        prj_id: Long // REFERENCES projects(prj_id) ON DELETE CASCADE
)

object TaskCategoryDTO {
    implicit val format: OFormat[TaskCategoryDTO] = Json.format[TaskCategoryDTO]

    /**
     * create task_category DTO entity
     * @param task_category
     * @return task_category DTO entity
     */
    def create(task_category: TaskCategory): TaskCategoryDTO = {
        TaskCategoryDTO(
            tsc_id = task_category.tsc_id,
            tsc_name = task_category.tsc_name,
            tsc_created_at = task_category.tsc_created_at,
            tsc_updated_at = task_category.tsc_updated_at,
            tsc_deleted_at = task_category.tsc_deleted_at,
            prj_id = task_category.prj_id
        )
    }
}