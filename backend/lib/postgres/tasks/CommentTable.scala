package demo1.taskmanagement.lib.postgres.tasks

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future
import java.time.{LocalDate, LocalDateTime}

class CommentTable(tag: Tag) extends Table[Comment](tag, "comments") {
        def cmt_id = column[Long]("cmt_id", O.PrimaryKey, O.AutoInc)

        def cmt_content = column[Option[String]]("cmt_content")

        def cmt_created_at = column[LocalDateTime]("cmt_created_at")
        def cmt_updated_at = column[Option[LocalDateTime]]("cmt_updated_at")
        def cmt_deleted_at = column[Option[LocalDateTime]]("cmt_deleted_at")

        def usr_id = column[Long]("usr_id")
        def tsk_id = column[Long]("tsk_id")

    def * = (
                cmt_id,
                cmt_content,
                cmt_created_at,
                cmt_updated_at,
                cmt_deleted_at,
                usr_id,
                tsk_id
    ).mapTo[Comment]
}