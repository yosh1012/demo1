package demo1.taskmanagement.lib.postgres.notifications

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class NotificationRepository(db: Database) {
    private val notifications = TableQuery[NotificationTable]

    def findById(id: Long): Future[Option[Notification]] = 
        db.run(notifications.filter(_.ntf_id === id).result.headOption)

    def findAll(): Future[Seq[Notification]] = 
        db.run(notifications.result)
}
