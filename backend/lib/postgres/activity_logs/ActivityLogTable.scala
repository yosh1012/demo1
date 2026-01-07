package com.taskmanagement.lib.postgres.activity_logs.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future
import java.time.{LocalDate, LocalDateTime}

class ActivityLogTable(tag: Tag) extends Table[ActivityLog](tag, "activity_logs") {
	def act_id = column[Long]("act_id", O.PrimaryKey, O.AutoInc)

	def act_action = column[String]("act_action")
	def act_details = column[String]("act_details")

	def act_created_at = column[LocalDateTime]("act_created_at")
	def act_updated_at = column[Option[LocalDateTime]]("act_updated_at")
    def act_deleted_at = column[Option[LocalDateTime]]("act_deleted_at")

	def usr_id = column[Long]("usr_id")

    def * = (
		act_id,

		act_action,
		act_details,

		act_created_at,
		act_updated_at,
		act_deleted_at,

		usr_id
    ).mapTo[ActivityLog]
}