package com.taskmanagement.workspaces.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class BridgeWorkspaceUserTable(tag: Tag) extends Table[BridgeWorkspaceUser](tag, "workspaces_users") {
    def column_name = column[Type]("column_name", O.PrimaryKey, O.AutoInc)

    def wks_usr_created_at = column[java.time.LocalDateTime]("wks_usr_created_at") // DEFAULT CURRENT_TIMESTAMP
    def wks_usr_updated_at = column[Option[java.time.LocalDateTime]]("wks_usr_updated_at")
    def wks_usr_deleted_at = column[Option[java.time.LocalDateTime]]("wks_usr_deleted_at")

    def * = (
        column_name1, column_name2, column_name3, column_name4,
        column_name5, column_name6, column_name7
    ).mapTo[BridgeWorkspaceUser]
}

class BridgeWorkspaceUserRepository(db: Database) {
    private val workspaces_users = TableQuery[BridgeWorkspaceUserTable]

    def findById(id: Long): Future[Option[BridgeWorkspaceUser]] = 
        db.run(workspaces_users.filter(_.wks_usr_id === id).result.headOption)

    def findAll(): Future[Seq[BridgeWorkspaceUser]] = 
        db.run(workspaces_users.result)
    
}
