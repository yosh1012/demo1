package demo1.taskmanagement.lib.postgres.tasks

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

final case class Attachment (
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
        cmt_id: Option[Long], // REFERENCES comments(cmt_id)
)
