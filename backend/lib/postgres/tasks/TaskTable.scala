package demo1.taskmanagement.lib.postgres.tasks

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future
import java.time.{LocalDate, LocalDateTime}

class TaskTable(tag: Tag) extends Table[Task](tag, "tasks") {

        implicit val taskStatusMapper = MappedColumnType.base[String, String](
        s => s, // Scala (String) -> DB (task_status)
        s => s  // DB (task_status) -> Scala (String)
        )
    
        def tsk_id = column[Long]("tsk_id", O.PrimaryKey, O.AutoInc)

        def tsk_name = column[String]("tsk_name")
        def tsk_description = column[Option[String]]("tsk_description")
        def tsk_description_version = column[Long]("tsk_description_version")
        def tsk_start_date = column[Option[LocalDate]]("tsk_start_date")
        def tsk_due_date = column[Option[LocalDate]]("tsk_due_date")
        def tsk_estimated_hours = column[Option[BigDecimal]]("tsk_estimated_hours")
        def tsk_status = column[String]("tsk_status", O.SqlType("task_status"))

        def tsk_created_at = column[LocalDateTime]("tsk_created_at")
        def tsk_updated_at = column[Option[LocalDateTime]]("tsk_updated_at")
        def tsk_deleted_at = column[Option[LocalDateTime]]("tsk_deleted_at")

        def prj_id = column[Long]("prj_id")

        def spr_id = column[Option[Long]]("spr_id")

    def * = (
                tsk_id,
                tsk_name,
                tsk_description,
                tsk_description_version,
                tsk_start_date,
                tsk_due_date,
                tsk_estimated_hours,
                tsk_status,
                tsk_created_at,
                tsk_updated_at,
                tsk_deleted_at,
                prj_id,
                spr_id
    ).mapTo[Task]
}
