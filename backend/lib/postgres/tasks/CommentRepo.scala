package demo1.taskmanagement.lib.postgres.tasks

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class CommentRepository(db: Database) {
    private val comments = TableQuery[CommentTable]

    def findById(id: Long): Future[Option[Comment]] = 
        db.run(comments.filter(_.cmt_id === id).result.headOption)

    def findAll(): Future[Seq[Comment]] = 
        db.run(comments.result)
    
}
