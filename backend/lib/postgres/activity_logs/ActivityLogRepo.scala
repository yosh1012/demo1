package com.taskmanagement.lib.postgres.activity_logs.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class ActivityLogRepository(db: Database) {
    private val activity_logs = TableQuery[ActivityLogTable]

    def findById(id: Long): Future[Option[ActivityLog]] = 
        db.run(activity_logs.filter(_.act_id === id).result.headOption)

    def findAll(): Future[Seq[ActivityLog]] = 
        db.run(activity_logs.result)
    
}
