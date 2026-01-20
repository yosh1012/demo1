package demo1.taskmanagement.lib.postgres.workspaces

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

final case class BridgeWorkspaceUser(
        wks_usr_id: Long, // PK
        
        wks_usr_is_manager: Boolean, // DEFAULT false

        wks_usr_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP,
        wks_usr_updated_at: Option[LocalDateTime],
        wks_usr_deleted_at: Option[LocalDateTime],

        wks_id: Long, //REFERENCES workspaces(wks_id) ON DELETE CASCADE,
        usr_id: Long //REFERENCES users(usr_id) ON DELETE CASCADE
)

object BridgeWorkspaceUser {
    implicit val format: OFormat[BridgeWorkspaceUser] = Json.format[BridgeWorkspaceUser]
}