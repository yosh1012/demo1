package com.taskmanagement.tasks.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class CommentTable(tag: Tag) extends Table[Comment](tag, "comments") {
    def column_name = column[Type]("column_name", O.PrimaryKey, O.AutoInc)

    def cmt_created_at = column[java.time.LocalDateTime]("cmt_created_at") // DEFAULT CURRENT_TIMESTAMP
    def cmt_updated_at = column[Option[java.time.LocalDateTime]]("cmt_updated_at")
    def cmt_deleted_at = column[Option[java.time.LocalDateTime]]("cmt_deleted_at")

    def * = (
        column_name1, column_name2, column_name3, column_name4,
        column_name5, column_name6, column_name7
    ).mapTo[Comment]
}

class CommentRepository(db: Database) {
    private val comments = TableQuery[CommentTable]

    def findById(id: Long): Future[Option[Comment]] = 
        db.run(comments.filter(_.cmt_id === id).result.headOption)

    def findAll(): Future[Seq[Comment]] = 
        db.run(comments.result)
    
}
