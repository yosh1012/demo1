package demo1.taskmanagement.lib.postgres.workspaces

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class WorkspaceRepository(db: Database) {
    private val workspaces = TableQuery[WorkspaceTable]

    def findById(id: Long): Future[Option[Workspace]] = 
        db.run(workspaces.filter(_.wks_id === id).result.headOption)

    def findAll(): Future[Seq[Workspace]] = 
        db.run(workspaces.result)
    
}
