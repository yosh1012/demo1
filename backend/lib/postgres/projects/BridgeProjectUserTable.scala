package demo1.taskmanagement.lib.postgres.projects

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future
import java.time.{LocalDate, LocalDateTime}

class BridgeProjectUserTable(tag: Tag) extends Table[BridgeProjectUser](tag, "projects_users") {
        def prj_usr_id = column[Long]("prj_usr_id", O.PrimaryKey, O.AutoInc)

        def prj_usr_is_lead = column[Boolean]("prj_usr_is_lead")
        def prj_usr_is_contributor = column[Boolean]("prj_usr_is_contributor")

        def prj_usr_created_at = column[LocalDateTime]("prj_usr_created_at")
        def prj_usr_updated_at = column[Option[LocalDateTime]]("prj_usr_updated_at")
        def prj_usr_deleted_at = column[Option[LocalDateTime]]("prj_usr_deleted_at")

        def prj_id = column[Long]("prj_id")
        def usr_id = column[Long]("usr_id")

    def * = (
                prj_usr_id,
                prj_usr_is_lead,
                prj_usr_is_contributor,
                prj_usr_created_at,
                prj_usr_updated_at,
                prj_usr_deleted_at,
                prj_id,
                usr_id
    ).mapTo[BridgeProjectUser]
}