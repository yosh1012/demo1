package com.taskmanagement.workspaces.demo1

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

case class Workspace(
        wks_id: Long, // PK

        wks_name: String,
        wks_description: Option[String],
        wks_is_archived: Boolean, // DEFAULT false
        wks_archived_at: Option[LocalDateTime],
        wks_is_opened: Boolean, // DEFAULT false

        wks_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP,
        wks_updated_at: Option[LocalDateTime],
        wks_deleted_at: Option[LocalDateTime],

        org_id: Long, // REFERENCES organizations(org_id) ON DELETE CASCADE
        
        usr_id_who_archived: Option[Long], // REFERENCES users(usr_id)
)

object Workspace {
    implicit val format: OFormat[Workspace] = Json.format[Workspace]
}