package com.taskmanagement.lib.postgres.tasks.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future
import java.time.{LocalDate, LocalDateTime}

class BridgeTaskUserTable(tag: Tag) extends Table[BridgeTaskUser](tag, "tasks_users") {
        def tsk_usr_id = column[Long]("tsk_usr_id", O.PrimaryKey, O.AutoInc)

        def tsk_usr_created_at = column[LocalDateTime]("tsk_usr_created_at")
        def tsk_usr_updated_at = column[Option[LocalDateTime]]("tsk_usr_updated_at")
        def tsk_usr_deleted_at = column[Option[LocalDateTime]]("tsk_usr_deleted_at")

        def tsk_id = column[Long]("tsk_id")
        def usr_id = column[Long]("usr_id")

    def * = (
                tsk_usr_id,
                tsk_usr_created_at,
                tsk_usr_updated_at,
                tsk_usr_deleted_at,
                tsk_id,
                usr_id
    ).mapTo[BridgeTaskUser]
}
