package com.taskmanagement.lib.postgres.work_times.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future
import java.time.{LocalDate, LocalDateTime}

class WorkTimeTable(tag: Tag) extends Table[WorkTime](tag, "work_times") {
        def wkt_id = column[Long]("wkt_id", O.PrimaryKey, O.AutoInc)

        def wkt_hours = column[BigDecimal]("wkt_hours")
        def wkt_date = column[LocalDate]("wkt_date")
        def wkt_description = column[Option[String]]("wkt_description")

        def wkt_created_at = column[LocalDateTime]("wkt_created_at")
        def wkt_updated_at = column[Option[LocalDateTime]]("wkt_updated_at")
        def wkt_deleted_at = column[Option[LocalDateTime]]("wkt_deleted_at")

        def usr_id = column[Long]("usr_id")
        def tsk_id = column[Long]("tsk_id")


    def * = (
                wkt_id,
                wkt_hours,
                wkt_date,
                wkt_description,
                wkt_created_at,
                wkt_updated_at,
                wkt_deleted_at,
                usr_id,
                tsk_id
    ).mapTo[WorkTime]
}