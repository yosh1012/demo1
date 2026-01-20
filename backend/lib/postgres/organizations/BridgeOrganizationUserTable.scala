package demo1.taskmanagement.lib.postgres.organizations

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future
import java.time.{LocalDate, LocalDateTime}

class BridgeOrganizationUserTable(tag: Tag) extends Table[BridgeOrganizationUser](tag, "organizations_users") {
        def org_usr_id = column[Long]("org_usr_id", O.PrimaryKey, O.AutoInc)

        def org_usr_is_admin = column[Boolean]("org_usr_is_admin")

        def org_usr_created_at = column[LocalDateTime]("org_usr_created_at")
        def org_usr_updated_at = column[Option[LocalDateTime]]("org_usr_updated_at")
        def org_usr_deleted_at = column[Option[LocalDateTime]]("org_usr_deleted_at")

        def org_id = column[Long]("org_id")
        def usr_id = column[Long]("usr_id")

    def * = (
                org_usr_id,
                org_usr_is_admin,
                org_usr_created_at,
                org_usr_updated_at,
                org_usr_deleted_at,
                org_id,
                usr_id
    ).mapTo[BridgeOrganizationUser]
}