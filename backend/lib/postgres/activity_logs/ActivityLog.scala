package com.taskmanagement.lib.postgres.activity_logs.demo1

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

final case class ActivityLog(
	act_id: Long, // PK

	act_action: String,
	act_details: String,

	act_created_at: LocalDateTime, //DEFAULT CURRENT_TIMESTAMP
	act_updated_at: Option[LocalDateTime],
    act_deleted_at: Option[LocalDateTime],

	usr_id: Long // REFERENCES users(usr_id) ON DELETE CASCADE
)
