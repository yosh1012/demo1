package com.taskmanagement.tasks.demo1

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

case class Task(
        tsk_id: Long, // PK

        tsk_name: String,
        tsk_description: Option[String],
        tsk_description_version: Long, // DEFAULT 1
        tsk_start_date: Option[LocalDate],
        tsk_due_date: Option[LocalDate],
        tsk_estimated_hours: Option[BigDecimal],
        tsk_status: String, // ENUM: TASK_STATUS DEFAULT 'todo'

        tsk_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP
        tsk_updated_at: Option[LocalDateTime],
        tsk_deleted_at: Option[LocalDateTime],

        prj_id: Long, // REFERENCES projects(prj_id) ON DELETE CASCADE

        spr_id: Option[Long] // REFERENCES sprints(spr_id) ON DELETE CASCADE
)

object Task {
    implicit val format: OFormat[Task] = Json.format[Task]
}