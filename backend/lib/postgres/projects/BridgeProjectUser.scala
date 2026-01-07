package com.taskmanagement.lib.postgres.projects.demo1

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

case class BridgeProjectUser(
        prj_usr_id: Long, // PK

        prj_usr_is_lead: Boolean, // DEFAULT false
        prj_usr_is_contributor: Boolean, // DEFAULT false

        prj_usr_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP
        prj_usr_updated_at: Option[LocalDateTime],
        prj_usr_deleted_at: Option[LocalDateTime],

        prj_id: Long, // REFERENCES projects(prj_id) ON DELETE CASCADE,
        usr_id: Long // REFERENCES users(usr_id) ON DELETE CASCADE
)

object BridgeProjectUser {
    implicit val format: OFormat[BridgeProjectUser] = Json.format[BridgeProjectUser]
}