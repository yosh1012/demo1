package com.taskmanagement.lib.postgres.tasks.demo1

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

final case class CommentDTO (
        cmt_id: Long, // PK

        cmt_content: Option[String],

        cmt_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP
        cmt_updated_at: Option[LocalDateTime],
        cmt_deleted_at: Option[LocalDateTime],

        usr_id: Long, // REFERENCES users(usr_id) ON DELETE CASCADE
        tsk_id: Long // REFERENCES tasks(tsk_id) ON DELETE CASCADE
)

object CommentDTO {
    implicit val format: OFormat[CommentDTO] = Json.format[CommentDTO]

    /**
     * create comment DTO entity
     * @param comment
     * @return comment DTO entity
     */
    def create(comment: Comment): CommentDTO = {
        CommentDTO(
            cmt_id = comment.cmt_id,
            cmt_content = comment.cmt_content,
            cmt_created_at = comment.cmt_created_at,
            cmt_updated_at = comment.cmt_updated_at,
            cmt_deleted_at = comment.cmt_deleted_at,
            usr_id = comment.usr_id,
            tsk_id = comment.tsk_id
        )
    }
}