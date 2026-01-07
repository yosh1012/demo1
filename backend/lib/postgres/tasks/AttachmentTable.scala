package com.taskmanagement.lib.postgres.tasks.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future
import java.time.{LocalDate, LocalDateTime}

class AttachmentTable(tag: Tag) extends Table[Attachment](tag, "attachments") {
        def atc_id = column[Long]("atc_id", O.PrimaryKey, O.AutoInc)

        def atc_url = column[String]("atc_url")
        def atc_file_name = column[String]("atc_file_name")
        def atc_file_size = column[Option[Long]]("atc_file_size")
        def atc_mime_type = column[Option[String]]("atc_mime_type")

        def atc_created_at = column[LocalDateTime]("atc_created_at")
        def atc_updated_at = column[Option[LocalDateTime]]("atc_updated_at")
        def atc_deleted_at = column[Option[LocalDateTime]]("atc_deleted_at")

        def usr_id = column[Long]("usr_id")
        def tsk_id = column[Long]("tsk_id")
        def cmt_id = column[Option[Long]]("cmt_id")

    def * = (
                atc_id,
                atc_url,
                atc_file_name,
                atc_file_size,
                atc_mime_type,
                atc_created_at,
                atc_updated_at,
                atc_deleted_at,
                usr_id,
                tsk_id,
                cmt_id
    ).mapTo[Attachment]
}