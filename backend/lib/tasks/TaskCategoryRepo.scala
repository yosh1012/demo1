package com.taskmanagement.tasks.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class TaskCategoryTable(tag: Tag) extends Table[TaskCategory](tag, "task_categories") {
    def column_name = column[Type]("column_name", O.PrimaryKey, O.AutoInc)

    def tsc_created_at = column[java.time.LocalDateTime]("tsc_created_at") // DEFAULT CURRENT_TIMESTAMP
    def tsc_updated_at = column[Option[java.time.LocalDateTime]]("tsc_updated_at")
    def tsc_deleted_at = column[Option[java.time.LocalDateTime]]("tsc_deleted_at")

    def * = (
        column_name1, column_name2, column_name3, column_name4,
        column_name5, column_name6, column_name7
    ).mapTo[TaskCategory]
}

class TaskCategoryRepository(db: Database) {
    private val task_categories = TableQuery[TaskCategoryTable]

    def findById(id: Long): Future[Option[TaskCategory]] = 
        db.run(task_categories.filter(_.tsc_id === id).result.headOption)

    def findAll(): Future[Seq[TaskCategory]] = 
        db.run(task_categories.result)
    
}
