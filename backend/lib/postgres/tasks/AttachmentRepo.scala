package demo1.taskmanagement.lib.postgres.tasks

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class AttachmentRepository(db: Database) {
    private val attachments = TableQuery[AttachmentTable]

    def findById(id: Long): Future[Option[Attachment]] = 
        db.run(attachments.filter(_.atc_id === id).result.headOption)

    def findAll(): Future[Seq[Attachment]] = 
        db.run(attachments.result)
    
}
