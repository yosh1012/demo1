package com.taskmanagement.notifications.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class NotificationTable(tag: Tag) extends Table[Notification](tag, "notifications") {
    def column_name = column[Type]("column_name", O.PrimaryKey, O.AutoInc)

    def ntf_created_at = column[java.time.LocalDateTime]("ntf_created_at") // DEFAULT CURRENT_TIMESTAMP
    def ntf_updated_at = column[Option[java.time.LocalDateTime]]("ntf_updated_at")
    def ntf_deleted_at = column[Option[java.time.LocalDateTime]]("ntf_deleted_at")

    def * = (
        column_name1, column_name2, column_name3, column_name4,
        column_name5, column_name6, column_name7
    ).mapTo[Notification]
}

class NotificationRepository(db: Database) {
    private val notifications = TableQuery[NotificationTable]

    def findById(id: Long): Future[Option[Notification]] = 
        db.run(notifications.filter(_.ntf_id === id).result.headOption)

    def findAll(): Future[Seq[Notification]] = 
        db.run(notifications.result)
    
}
