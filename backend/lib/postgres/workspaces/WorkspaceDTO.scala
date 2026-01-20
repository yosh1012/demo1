package demo1.taskmanagement.lib.postgres.workspaces

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

final case class WorkspaceDTO (
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

object WorkspaceDTO {
    implicit val format: OFormat[WorkspaceDTO] = Json.format[WorkspaceDTO]

    /**
     * create workspace DTO entity
     * @param workspace
     * @return workspace DTO entity
     */
    def create(workspace: Workspace): WorkspaceDTO = {
        WorkspaceDTO(
            wks_id = workspace.wks_id,
            wks_name = workspace.wks_name,
            wks_description = workspace.wks_description,
            wks_is_archived = workspace.wks_is_archived,
            wks_archived_at = workspace.wks_archived_at,
            wks_is_opened = workspace.wks_is_opened,
            wks_created_at = workspace.wks_created_at,
            wks_updated_at = workspace.wks_updated_at,
            wks_deleted_at = workspace.wks_deleted_at,
            org_id = workspace.org_id,
            usr_id_who_archived = workspace.usr_id_who_archived
        )
    }
}