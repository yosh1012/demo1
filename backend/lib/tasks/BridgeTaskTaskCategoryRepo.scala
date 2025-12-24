package com.taskmanagement.tasks.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class BridgeTaskTaskCategoryTable(tag: Tag) extends Table[BridgeTaskTaskCategory](tag, "tasks_task_categories") {
    def column_name = column[Type]("column_name", O.PrimaryKey, O.AutoInc)

    def tsk_tsc_created_at = column[java.time.LocalDateTime]("tsk_tsc_created_at") // DEFAULT CURRENT_TIMESTAMP
    def tsk_tsc_updated_at = column[Option[java.time.LocalDateTime]]("tsk_tsc_updated_at")
    def tsk_tsc_deleted_at = column[Option[java.time.LocalDateTime]]("tsk_tsc_deleted_at")

    def * = (
        column_name1, column_name2, column_name3, column_name4,
        column_name5, column_name6, column_name7
    ).mapTo[BridgeTaskTaskCategory]
}

class BridgeTaskTaskCategoryRepository(db: Database) {
    private val tasks_task_categories = TableQuery[BridgeTaskTaskCategoryTable]

    def findById(id: Long): Future[Option[BridgeTaskTaskCategory]] = 
        db.run(tasks_task_categories.filter(_.tsk_tsc_id === id).result.headOption)

    def findAll(): Future[Seq[BridgeTaskTaskCategory]] = 
        db.run(tasks_task_categories.result)
    
}
