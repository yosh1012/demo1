package com.taskmanagement.lib.postgres.sprints.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class SprintRepository(db: Database) {
    private val sprints = TableQuery[SprintTable]

    def findById(id: Long): Future[Option[Sprint]] = 
        db.run(sprints.filter(_.spr_id === id).result.headOption)

    def findAll(): Future[Seq[Sprint]] = 
        db.run(sprints.result)
    
}
