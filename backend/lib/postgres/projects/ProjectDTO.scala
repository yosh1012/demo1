package demo1.taskmanagement.lib.postgres.projects

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

final case class ProjectDTO (
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

object ProjectDTO {
    implicit val format: OFormat[ProjectDTO] = Json.format[ProjectDTO]

    /**
     * create project DTO entity
     * @param project
     * @return project DTO entity
     */
    def create(project: Project): ProjectDTO = {
        ProjectDTO(
            prj_id = project.prj_id,
            prj_name = project.prj_name,
            prj_description = project.prj_description,
            prj_is_archived = project.prj_is_archived,
            prj_archived_at = project.prj_archived_at,
            prj_created_at = project.prj_created_at,
            prj_updated_at = project.prj_updated_at,
            prj_deleted_at = project.prj_deleted_at,
            wks_id = project.wks_id,
            usr_id_who_archived = project.usr_id_who_archived
        )
    }
}
