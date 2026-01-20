package demo1.taskmanagement.lib.postgres.tasks

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

final case class TaskCategory (
        tsc_id: Long, // PK

        tsc_name: String,

        tsc_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP,
        tsc_updated_at: Option[LocalDateTime],
        tsc_deleted_at: Option[LocalDateTime],

        prj_id: Long // REFERENCES projects(prj_id) ON DELETE CASCADE
)
