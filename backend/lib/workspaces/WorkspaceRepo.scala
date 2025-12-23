package com.taskmanagement.workspaces.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class WorkspaceTable(tag: Tag) extends Table[Workspace](tag, "workspaces") {
    def column_name = column[Type]("column_name", O.PrimaryKey, O.AutoInc)

    def wks_created_at = column[java.time.LocalDateTime]("wks_created_at") // DEFAULT CURRENT_TIMESTAMP
    def wks_updated_at = column[Option[java.time.LocalDateTime]]("wks_updated_at")
    def wks_deleted_at = column[Option[java.time.LocalDateTime]]("wks_deleted_at")

    def * = (
        column_name1, column_name2, column_name3, column_name4,
        column_name5, column_name6, column_name7
    ).mapTo[Workspace]
}

class WorkspaceRepository(db: Database) {
    private val workspaces = TableQuery[WorkspaceTable]

    def findById(id: Long): Future[Option[Workspace]] = 
        db.run(workspaces.filter(_.wks_id === id).result.headOption)

    def findAll(): Future[Seq[Workspace]] = 
        db.run(workspaces.result)
    
}
