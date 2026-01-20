package demo1.taskmanagement.lib.postgres.tasks

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

final case class AttachmentDTO (
        atc_id: Long, // PK

        atc_url: String,
        atc_file_name: String,
        atc_file_size: Option[Long],
        atc_mime_type: Option[String],

        atc_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP,
        atc_updated_at: Option[LocalDateTime],
        atc_deleted_at: Option[LocalDateTime],

        usr_id: Long, // REFERENCES users(usr_id) ON DELETE CASCADE,
        tsk_id: Long, // REFERENCES tasks(tsk_id) ON DELETE CASCADE,
        cmt_id: Option[Long] // REFERENCES comments(cmt_id)
)

object AttachmentDTO {
    implicit val format: OFormat[AttachmentDTO] = Json.format[AttachmentDTO]

    /**
     * create attachment DTO entity
     * @param attachment
     * @return attachment DTO entity
     */
    def create(attachment: Attachment): AttachmentDTO = {
        AttachmentDTO(
            atc_id = attachment.atc_id,
            atc_url = attachment.atc_url,
            atc_file_name = attachment.atc_file_name,
            atc_file_size = attachment.atc_file_size,
            atc_mime_type = attachment.atc_mime_type,
            atc_created_at = attachment.atc_created_at,
            atc_updated_at = attachment.atc_updated_at,
            atc_deleted_at = attachment.atc_deleted_at,
            usr_id = attachment.usr_id,
            tsk_id = attachment.tsk_id,
            cmt_id = attachment.cmt_id
        )
    }
}