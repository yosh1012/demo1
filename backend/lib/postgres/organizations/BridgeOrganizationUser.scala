package com.taskmanagement.lib.postgres.organizations.demo1

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

final case class BridgeOrganizationUser(
        org_usr_id: Long, // PK

        org_usr_is_admin: Boolean, // DEFAULT false,

        org_usr_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP,
        org_usr_updated_at: Option[LocalDateTime],
        org_usr_deleted_at: Option[LocalDateTime],

        org_id: Long, // REFERENCES organizations(org_id) ON DELETE CASCADE,
        usr_id: Long // REFERENCES users(usr_id) ON DELETE CASCADE
)

object BridgeOrganizationUser {
    implicit val format: OFormat[BridgeOrganizationUser] = Json.format[BridgeOrganizationUser]
}