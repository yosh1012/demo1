package com.taskmanagement.lib.postgres.activity_log.demo1

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

final case class ActivityLogDTO (
	act_id: Long, // PK

	act_action: String,
	act_details: String,

	act_created_at: LocalDateTime, //DEFAULT CURRENT_TIMESTAMP
	act_updated_at: Option[LocalDateTime],
    act_deleted_at: Option[LocalDateTime],

	usr_id: Long // REFERENCES users(usr_id) ON DELETE CASCADE
)

object ActivityLogDTO {
    implicit val format: OFormat[ActivityLogDTO] = Json.format[ActivityLogDTO]

    /**
     * create activity_log DTO entity
     * @param activity_log
     * @return activity_log DTO entity
     */
    def create(activity_log: ActivityLog): ActivityLogDTO = {
        ActivityLogDTO(
            act_id = activity_log.act_id,
            act_action = activity_log.act_action,
            act_details = activity_log.act_details,
            act_created_at = activity_log.act_created_at,
            act_updated_at = activity_log.act_updated_at,
            act_deleted_at = activity_log.act_deleted_at,
            usr_id = activity_log.usr_id
        )
    }
}
