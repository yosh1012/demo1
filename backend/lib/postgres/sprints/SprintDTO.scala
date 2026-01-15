package com.taskmanagement.lib.postgres.sprints.demo1

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

final case class SprintDTO (
        spr_id: Long, // PK
        spr_name: String,

        spr_start_date: LocalDate,
        spr_end_date: LocalDate,
    
        spr_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP,
        spr_updated_at: Option[LocalDateTime],
        spr_deleted_at: Option[LocalDateTime],

        prj_id: Long // REFERENCES projects(prj_id) ON DELETE CASCADE
)

object SprintDTO {
    implicit val format: OFormat[SprintDTO] = Json.format[SprintDTO]

    /**
     * create sprint DTO entity
     * @param sprint
     * @return sprint DTO entity
     */
    def create(sprint: Sprint): SprintDTO = {
        SprintDTO(
            spr_id = sprint.spr_id,
            spr_name = sprint.spr_name,
            spr_start_date = sprint.spr_start_date,
            spr_end_date = sprint.spr_end_date,
            spr_created_at = sprint.spr_created_at,
            spr_updated_at = sprint.spr_updated_at,
            spr_deleted_at = sprint.spr_deleted_at,
            prj_id = sprint.prj_id
        )
    }
}
