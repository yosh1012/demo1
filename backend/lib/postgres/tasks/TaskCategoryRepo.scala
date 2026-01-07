package com.taskmanagement.lib.postgres.tasks.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class TaskCategoryRepository(db: Database) {
    private val task_categories = TableQuery[TaskCategoryTable]

    def findById(id: Long): Future[Option[TaskCategory]] = 
        db.run(task_categories.filter(_.tsc_id === id).result.headOption)

    def findAll(): Future[Seq[TaskCategory]] = 
        db.run(task_categories.result)
    
}
