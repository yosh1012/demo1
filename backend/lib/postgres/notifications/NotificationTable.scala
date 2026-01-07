package com.taskmanagement.lib.postgres.notifications.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future
import java.time.{LocalDate, LocalDateTime}

class NotificationTable(tag: Tag) extends Table[Notification](tag, "notifications") {
        def ntf_id = column[Long]("ntf_id", O.PrimaryKey, O.AutoInc)

        def ntf_content = column[Option[String]]("ntf_content")
        def ntf_is_read = column[Boolean]("ntf_is_read")
        def ntf_read_at = column[Option[LocalDateTime]]("ntf_read_at")
        def ntf_level = column[String]("ntf_level")

        def ntf_created_at = column[LocalDateTime]("ntf_created_at")
        def ntf_updated_at = column[Option[LocalDateTime]]("ntf_updated_at")
        def ntf_deleted_at = column[Option[LocalDateTime]]("ntf_deleted_at")

        def usr_id = column[Long]("usr_id")

    def * = (
                ntf_id,
                ntf_content,
                ntf_is_read,
                ntf_read_at,
                ntf_level,
                ntf_created_at,
                ntf_updated_at,
                ntf_deleted_at,
                usr_id
    ).mapTo[Notification]
}
