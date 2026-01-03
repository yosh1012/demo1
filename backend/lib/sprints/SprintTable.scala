package com.taskmanagement.sprints.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future
import java.time.{LocalDate, LocalDateTime}

class SprintTable(tag: Tag) extends Table[Sprint](tag, "sprints") {
        def spr_id = column[Long]("spr_id", O.PrimaryKey, O.AutoInc)

        def spr_name = column[String]("spr_name")
        def spr_start_date = column[LocalDate]("spr_start_date")
        def spr_end_date = column[LocalDate]("spr_end_date")

        def spr_created_at = column[LocalDateTime]("spr_created_at")
        def spr_updated_at = column[Option[LocalDateTime]]("spr_updated_at")
        def spr_deleted_at = column[Option[LocalDateTime]]("spr_deleted_at")

        def prj_id = column[Long]("prj_id")

    def * = (
                spr_id,
                spr_name,
                spr_start_date,
                spr_end_date,
                spr_created_at,
                spr_updated_at,
                spr_deleted_at,
                prj_id
    ).mapTo[Sprint]
}
