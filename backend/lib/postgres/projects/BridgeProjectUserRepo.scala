package com.taskmanagement.projects.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class BridgeProjectUserTable(tag: Tag) extends Table[BridgeProjectUser](tag, "projects_users") {
    def column_name = column[Type]("column_name", O.PrimaryKey, O.AutoInc)

    def prj_usr_created_at = column[java.time.LocalDateTime]("prj_usr_created_at") // DEFAULT CURRENT_TIMESTAMP
    def prj_usr_updated_at = column[Option[java.time.LocalDateTime]]("prj_usr_updated_at")
    def prj_usr_deleted_at = column[Option[java.time.LocalDateTime]]("prj_usr_deleted_at")

    def * = (
        column_name1, column_name2, column_name3, column_name4,
        column_name5, column_name6, column_name7
    ).mapTo[BridgeProjectUser]
}

class BridgeProjectUserRepository(db: Database) {
    private val projects_users = TableQuery[BridgeProjectUserTable]

    def findById(id: Long): Future[Option[BridgeProjectUser]] = 
        db.run(projects_users.filter(_.prj_usr_id === id).result.headOption)

    def findAll(): Future[Seq[BridgeProjectUser]] = 
        db.run(projects_users.result)
    
}
