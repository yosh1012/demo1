package com.taskmanagement.lib.postgres.work_times.demo1

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

case class WorkTimeDTO (
        wkt_id: Long, // PK

        wkt_hours: BigDecimal, // DEFAULT 0.00
        wkt_date: LocalDate,
        wkt_description: Option[String],

        wkt_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP,
        wkt_updated_at: Option[LocalDateTime],
        wkt_deleted_at: Option[LocalDateTime],

        usr_id: Long, // REFERENCES users(usr_id) ON DELETE CASCADE,
        tsk_id: Long // REFERENCES tasks(tsk_id) ON DELETE CASCADE
)

object WorkTimeDTO {
    implicit val format: OFormat[WorkTimeDTO] = Json.format[WorkTimeDTO]
}
