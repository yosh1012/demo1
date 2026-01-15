package com.taskmanagement.lib.postgres.tasks.demo1

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

final case class BridgeTaskUser(
        tsk_usr_id: Long, // PK

        tsk_usr_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP
        tsk_usr_updated_at: Option[LocalDateTime],
        tsk_usr_deleted_at: Option[LocalDateTime],

        tsk_id: Long, // REFERENCES tasks(tsk_id) ON DELETE CASCADE
        usr_id: Long // REFERENCES users(usr_id) ON DELETE CASCADE
)

object BridgeTaskUser {
    implicit val format: OFormat[BridgeTaskUser] = Json.format[BridgeTaskUser]
}