package com.taskmanagement.lib.postgres.work_times.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class WorkTimeRepository(db: Database) {
    private val work_times = TableQuery[WorkTimeTable]

    def findById(id: Long): Future[Option[WorkTime]] = 
        db.run(work_times.filter(_.wkt_id === id).result.headOption)

    def findAll(): Future[Seq[WorkTime]] = 
        db.run(work_times.result)
    
}
