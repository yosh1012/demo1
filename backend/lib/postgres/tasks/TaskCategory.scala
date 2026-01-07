package com.taskmanagement.lib.postgres.tasks.demo1

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

case class TaskCategory(
        tsc_id: Long, // PK

        tsc_name: String,

        tsc_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP,
        tsc_updated_at: Option[LocalDateTime],
        tsc_deleted_at: Option[LocalDateTime],

        prj_id: Long // REFERENCES projects(prj_id) ON DELETE CASCADE
)

object TaskCategory {
    implicit val format: OFormat[TaskCategory] = Json.format[TaskCategory]
}