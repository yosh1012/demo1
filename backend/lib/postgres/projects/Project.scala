package com.taskmanagement.lib.postgres.projects.demo1

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

case class Project(
        prj_id: Long, // PK

        prj_name: String,
        prj_description: Option[String],
        prj_is_archived: Boolean, // DEFAULT false
        prj_archived_at: Option[LocalDateTime],
        
        prj_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP
        prj_updated_at: Option[LocalDateTime],
        prj_deleted_at: Option[LocalDateTime],

        wks_id: Long, // REFERENCES workspaces(wks_id) ON DELETE CASCADE

        usr_id_who_archived: Option[Long] // REFERENCES users(usr_id)
)

object Project {
    implicit val format: OFormat[Project] = Json.format[Project]
}
