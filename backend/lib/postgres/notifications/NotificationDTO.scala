package com.taskmanagement.lib.postgres.notifications.demo1

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDateTime, LocalDate}

final case class NotificationDTO (
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

object NotificationDTO {
    implicit val format: OFormat[NotificationDTO] = Json.format[NotificationDTO]

    /**
     * create notification DTO entity
     * @param notification
     * @return notification DTO entity
     */
    def create(notification: Notification): NotificationDTO = {
        NotificationDTO(
            ntf_id = notification.ntf_id,
            ntf_content = notification.ntf_content,
            ntf_is_read = notification.ntf_is_read,
            ntf_read_at = notification.ntf_read_at,
            ntf_level = notification.ntf_level,
            ntf_created_at = notification.ntf_created_at,
            ntf_updated_at = notification.ntf_updated_at,
            ntf_deleted_at = notification.ntf_deleted_at,
            usr_id = notification.usr_id
        )
    }
}
