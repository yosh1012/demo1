package com.taskmanagement.tasks.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class TaskTable(tag: Tag) extends Table[Task](tag, "tasks") {
    def column_name = column[Type]("column_name", O.PrimaryKey, O.AutoInc)

    def tsk_created_at = column[java.time.LocalDateTime]("tsk_created_at") // DEFAULT CURRENT_TIMESTAMP
    def tsk_updated_at = column[Option[java.time.LocalDateTime]]("tsk_updated_at")
    def tsk_deleted_at = column[Option[java.time.LocalDateTime]]("tsk_deleted_at")

    def * = (
        column_name1, column_name2, column_name3, column_name4,
        column_name5, column_name6, column_name7
    ).mapTo[Task]
}

class TaskRepository(db: Database) {
    private val tasks = TableQuery[TaskTable]

    def findById(id: Long): Future[Option[Task]] = 
        db.run(tasks.filter(_.tsk_id === id).result.headOption)

    def findAll(): Future[Seq[Task]] = 
        db.run(tasks.result)
    
}
