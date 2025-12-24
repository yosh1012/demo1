package com.taskmanagement.tasks.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class AttachmentTable(tag: Tag) extends Table[Attachment](tag, "attachments") {
    def column_name = column[Type]("column_name", O.PrimaryKey, O.AutoInc)

    def atc_created_at = column[java.time.LocalDateTime]("atc_created_at") // DEFAULT CURRENT_TIMESTAMP
    def atc_updated_at = column[Option[java.time.LocalDateTime]]("atc_updated_at")
    def atc_deleted_at = column[Option[java.time.LocalDateTime]]("atc_deleted_at")

    def * = (
        column_name1, column_name2, column_name3, column_name4,
        column_name5, column_name6, column_name7
    ).mapTo[Attachment]
}

class AttachmentRepository(db: Database) {
    private val attachments = TableQuery[AttachmentTable]

    def findById(id: Long): Future[Option[Attachment]] = 
        db.run(attachments.filter(_.atc_id === id).result.headOption)

    def findAll(): Future[Seq[Attachment]] = 
        db.run(attachments.result)
    
}
