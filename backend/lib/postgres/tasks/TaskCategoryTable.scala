package com.taskmanagement.lib.postgres.tasks.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future
import java.time.{LocalDate, LocalDateTime}

class TaskCategoryTable(tag: Tag) extends Table[TaskCategory](tag, "task_categories") {
        def tsc_id = column[Long]("tsc_id", O.PrimaryKey, O.AutoInc)

        def tsc_name = column[String]("tsc_name")

        def tsc_created_at = column[LocalDateTime]("tsc_created_at")
        def tsc_updated_at = column[Option[LocalDateTime]]("tsc_updated_at")
        def tsc_deleted_at = column[Option[LocalDateTime]]("tsc_deleted_at")

        def prj_id = column[Long]("prj_id")

    def * = (
                tsc_id,
                tsc_name,
                tsc_created_at,
                tsc_updated_at,
                tsc_deleted_at,
                prj_id
    ).mapTo[TaskCategory]
}
