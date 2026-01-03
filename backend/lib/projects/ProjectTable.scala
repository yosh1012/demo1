package com.taskmanagement.projects.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future
import java.time.{LocalDate, LocalDateTime}

class ProjectTable(tag: Tag) extends Table[Project](tag, "projects") {
        def prj_id = column[Long]("prj_id", O.PrimaryKey, O.AutoInc)

        def prj_name = column[String]("prj_name")
        def prj_description = column[Option[String]]("prj_description")
        def prj_is_archived = column[Boolean]("prj_is_archived")
        def prj_archived_at = column[Option[LocalDateTime]]("prj_archived_at")

        def prj_created_at = column[LocalDateTime]("prj_created_at")
        def prj_updated_at = column[Option[LocalDateTime]]("prj_updated_at")
        def prj_deleted_at = column[Option[LocalDateTime]]("prj_deleted_at")

        def wks_id = column[Long]("wks_id")

        def usr_id_who_archived = column[Option[Long]]("usr_id_who_archived")

    def * = (
                prj_id,
                prj_name,
                prj_description,
                prj_is_archived,
                prj_archived_at,
                prj_created_at,
                prj_updated_at,
                prj_deleted_at,
                wks_id,
                usr_id_who_archived
    ).mapTo[Project]
}