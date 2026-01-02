package com.taskmanagement.work_times.demo1

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

case class WorkTime(
    column: type, // DEFALUT
    tbl_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP
    tbl_updated_at: Option[LocalDateTime],
    tbl_deleted_at: Option[LocalDateTime]
)
