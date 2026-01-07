package com.taskmanagement.lib.postgres.sprints.demo1

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

case class Sprint(
        spr_id: Long, // PK
        spr_name: String,

        spr_start_date: LocalDate,
        spr_end_date: LocalDate,
    
        spr_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP,
        spr_updated_at: Option[LocalDateTime],
        spr_deleted_at: Option[LocalDateTime],

        prj_id: Long // REFERENCES projects(prj_id) ON DELETE CASCADE
)

object Sprint {
    implicit val format: OFormat[Sprint] = Json.format[Sprint]
}
