package com.taskmanagement.lib.postgres.tasks.demo1

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

final case class Comment (
        cmt_id: Long, // PK

        cmt_content: Option[String],

        cmt_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP
        cmt_updated_at: Option[LocalDateTime],
        cmt_deleted_at: Option[LocalDateTime],

        usr_id: Long, // REFERENCES users(usr_id) ON DELETE CASCADE
        tsk_id: Long // REFERENCES tasks(tsk_id) ON DELETE CASCADE
)
