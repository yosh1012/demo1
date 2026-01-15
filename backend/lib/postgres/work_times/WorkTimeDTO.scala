package com.taskmanagement.lib.postgres.work_times.demo1

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

final case class WorkTimeDTO (
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

    /**
     * create work_time DTO entity
     * @param work_time
     * @return work_time DTO entity
     */
    def create(work_time: WorkTime): WorkTimeDTO = {
        WorkTimeDTO(
            wkt_id = work_time.wkt_id,
            wkt_hours = work_time.wkt_hours,
            wkt_date = work_time.wkt_date,
            wkt_description = work_time.wkt_description,
            wkt_created_at = work_time.wkt_created_at,
            wkt_updated_at = work_time.wkt_updated_at,
            wkt_deleted_at = work_time.wkt_deleted_at,
            usr_id = work_time.usr_id,
            tsk_id = work_time.tsk_id
        )
    }
}
