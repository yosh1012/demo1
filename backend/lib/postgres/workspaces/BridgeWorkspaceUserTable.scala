package demo1.taskmanagement.lib.postgres.workspaces

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future
import java.time.{LocalDate, LocalDateTime}

class BridgeWorkspaceUserTable(tag: Tag) extends Table[BridgeWorkspaceUser](tag, "workspaces_users") {
        def wks_usr_id = column[Long]("wks_usr_id", O.PrimaryKey, O.AutoInc)

        def wks_usr_is_manager = column[Boolean]("wks_usr_is_manager")

        def wks_usr_created_at = column[LocalDateTime]("wks_usr_created_at")
        def wks_usr_updated_at = column[Option[LocalDateTime]]("wks_usr_updated_at")
        def wks_usr_deleted_at = column[Option[LocalDateTime]]("wks_usr_deleted_at")

        def wks_id = column[Long]("wks_id")
        def usr_id = column[Long]("usr_id")


    def * = (
                wks_usr_id,
                wks_usr_is_manager,
                wks_usr_created_at,
                wks_usr_updated_at,
                wks_usr_deleted_at,
                wks_id,
                usr_id
    ).mapTo[BridgeWorkspaceUser]
}
