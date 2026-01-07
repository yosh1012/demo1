package com.taskmanagement.lib.postgres.tasks.demo1

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

case class BridgeTaskTaskCategory(
        tsk_tsc_id: Long, // PK

        tsk_tsc_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP,
        tsk_tsc_updated_at: Option[LocalDateTime],
        tsk_tsc_deleted_at: Option[LocalDateTime],

        tsk_id: Long, // REFERENCES tasks(tsk_id) ON DELETE CASCADE,
        tsc_id: Long // REFERENCES task_categories(tsc_id) ON DELETE CASCADE
)

object BridgeTaskTaskCategory {
    implicit val format: OFormat[BridgeTaskTaskCategory] = Json.format[BridgeTaskTaskCategory]
}