package com.taskmanagement.tasks.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class TaskRepository(db: Database) {
    private val tasks = TableQuery[TaskTable]

    def findById(id: Long): Future[Option[Task]] = 
        db.run(tasks.filter(_.tsk_id === id).result.headOption)

    def findAll(): Future[Seq[Task]] = 
        db.run(tasks.result)
    
}
