package com.taskmanagement.tasks.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class BridgeTaskUserTable(tag: Tag) extends Table[BridgeTaskUser](tag, "tasks_users") {
    def column_name = column[Type]("column_name", O.PrimaryKey, O.AutoInc)

    def tsk_usr_created_at = column[java.time.LocalDateTime]("tsk_usr_created_at") // DEFAULT CURRENT_TIMESTAMP
    def tsk_usr_updated_at = column[Option[java.time.LocalDateTime]]("tsk_usr_updated_at")
    def tsk_usr_deleted_at = column[Option[java.time.LocalDateTime]]("tsk_usr_deleted_at")

    def * = (
        column_name1, column_name2, column_name3, column_name4,
        column_name5, column_name6, column_name7
    ).mapTo[BridgeTaskUser]
}

class BridgeTaskUserRepository(db: Database) {
    private val tasks_users = TableQuery[BridgeTaskUserTable]

    def findById(id: Long): Future[Option[BridgeTaskUser]] = 
        db.run(tasks_users.filter(_.tsk_usr_id === id).result.headOption)

    def findAll(): Future[Seq[BridgeTaskUser]] = 
        db.run(tasks_users.result)
    
}
