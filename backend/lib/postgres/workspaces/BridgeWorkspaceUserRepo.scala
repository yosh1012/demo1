package demo1.taskmanagement.lib.postgres.workspaces

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class BridgeWorkspaceUserRepository(db: Database) {
    private val workspaces_users = TableQuery[BridgeWorkspaceUserTable]

    def findById(id: Long): Future[Option[BridgeWorkspaceUser]] = 
        db.run(workspaces_users.filter(_.wks_usr_id === id).result.headOption)

    def findAll(): Future[Seq[BridgeWorkspaceUser]] = 
        db.run(workspaces_users.result)
    
}
