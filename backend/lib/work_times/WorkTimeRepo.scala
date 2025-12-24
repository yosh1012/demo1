package com.taskmanagement.work_times.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class WorkTimeTable(tag: Tag) extends Table[WorkTime](tag, "work_times") {
    def column_name = column[Type]("column_name", O.PrimaryKey, O.AutoInc)

    def wkt_created_at = column[java.time.LocalDateTime]("wkt_created_at") // DEFAULT CURRENT_TIMESTAMP
    def wkt_updated_at = column[Option[java.time.LocalDateTime]]("wkt_updated_at")
    def wkt_deleted_at = column[Option[java.time.LocalDateTime]]("wkt_deleted_at")

    def * = (
        column_name1, column_name2, column_name3, column_name4,
        column_name5, column_name6, column_name7
    ).mapTo[WorkTime]
}

class WorkTimeRepository(db: Database) {
    private val work_times = TableQuery[WorkTimeTable]

    def findById(id: Long): Future[Option[WorkTime]] = 
        db.run(work_times.filter(_.wkt_id === id).result.headOption)

    def findAll(): Future[Seq[Task]] = 
        db.run(work_times.result)
    
}
