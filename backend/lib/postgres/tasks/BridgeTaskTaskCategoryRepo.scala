package com.taskmanagement.tasks.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class BridgeTaskTaskCategoryRepository(db: Database) {
    private val tasks_task_categories = TableQuery[BridgeTaskTaskCategoryTable]

    def findById(id: Long): Future[Option[BridgeTaskTaskCategory]] = 
        db.run(tasks_task_categories.filter(_.tsk_tsc_id === id).result.headOption)

    def findAll(): Future[Seq[BridgeTaskTaskCategory]] = 
        db.run(tasks_task_categories.result)
    
}
