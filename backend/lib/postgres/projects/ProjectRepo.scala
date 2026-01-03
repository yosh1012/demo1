package com.taskmanagement.projects.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class ProjectRepository(db: Database) {
    private val projects = TableQuery[ProjectTable]

    def findById(id: Long): Future[Option[Project]] = 
        db.run(projects.filter(_.prj_id === id).result.headOption)

    def findAll(): Future[Seq[Project]] = 
        db.run(projects.result)
    
}
