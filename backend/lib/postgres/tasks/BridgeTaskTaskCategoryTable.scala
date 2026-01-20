package demo1.taskmanagement.lib.postgres.tasks

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future
import java.time.{LocalDate, LocalDateTime}

class BridgeTaskTaskCategoryTable(tag: Tag) extends Table[BridgeTaskTaskCategory](tag, "tasks_task_categories") {
        def tsk_tsc_id = column[Long]("tsk_tsc_id", O.PrimaryKey, O.AutoInc)

        def tsk_tsc_created_at = column[LocalDateTime]("tsk_tsc_created_at")
        def tsk_tsc_updated_at = column[Option[LocalDateTime]]("tsk_tsc_updated_at")
        def tsk_tsc_deleted_at = column[Option[LocalDateTime]]("tsk_tsc_deleted_at")

        def tsk_id = column[Long]("tsk_id")
        def tsc_id = column[Long]("tsc_id")

    def * = (
                tsk_tsc_id,
                tsk_tsc_created_at,
                tsk_tsc_updated_at,
                tsk_tsc_deleted_at,
                tsk_id,
                tsc_id
    ).mapTo[BridgeTaskTaskCategory]
}