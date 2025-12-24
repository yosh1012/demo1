package com.taskmanagement.activity_logs.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class ActivityLogTable(tag: Tag) extends Table[ActivityLog](tag, "activity_logs") {
    def column_name = column[Type]("column_name", O.PrimaryKey, O.AutoInc)

    def act_created_at = column[java.time.LocalDateTime]("act_created_at") // DEFAULT CURRENT_TIMESTAMP
    def act_updated_at = column[Option[java.time.LocalDateTime]]("act_updated_at")
    def act_deleted_at = column[Option[java.time.LocalDateTime]]("act_deleted_at")

    def * = (
        column_name1, column_name2, column_name3, column_name4,
        column_name5, column_name6, column_name7
    ).mapTo[ActivityLog]
}

class ActivityLogRepository(db: Database) {
    private val activity_logs = TableQuery[ActivityLogTable]

    def findById(id: Long): Future[Option[ActivityLog]] = 
        db.run(activity_logs.filter(_.act_id === id).result.headOption)

    def findAll(): Future[Seq[ActivityLog]] = 
        db.run(activity_logs.result)
    
}
