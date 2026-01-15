package com.taskmanagement.lib.postgres.notifications.demo1

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDateTime, LocalDate}

final case class Notification(
        ntf_id: Long, // PK

        ntf_content: Option[String],
        ntf_is_read: Boolean, // DEFALUT false,
        ntf_read_at: Option[LocalDateTime],
        ntf_level: String, // ENUM: NOTIFICATION_LEVEL DEFAULT 'alert'

        ntf_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP
        ntf_updated_at: Option[LocalDateTime],
        ntf_deleted_at: Option[LocalDateTime],

        usr_id: Long // REFERENCES users(usr_id) ON DELETE CASCADE
)

