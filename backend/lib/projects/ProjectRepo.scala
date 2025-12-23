package com.taskmanagement.projects.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class ProjectTable(tag: Tag) extends Table[Project](tag, "projects") {
    def column_name = column[Type]("column_name", O.PrimaryKey, O.AutoInc)

    def prj_created_at = column[java.time.LocalDateTime]("prj_created_at") // DEFAULT CURRENT_TIMESTAMP
    def prj_updated_at = column[Option[java.time.LocalDateTime]]("prj_updated_at")
    def prj_deleted_at = column[Option[java.time.LocalDateTime]]("prj_deleted_at")

    def * = (
        column_name1, column_name2, column_name3, column_name4,
        column_name5, column_name6, column_name7
    ).mapTo[Project]
}

class ProjectRepository(db: Database) {
    private val projects = TableQuery[ProjectTable]

    def findById(id: Long): Future[Option[Project]] = 
        db.run(projects.filter(_.prj_id === id).result.headOption)

    def findAll(): Future[Seq[Project]] = 
        db.run(projects.result)
    
}
