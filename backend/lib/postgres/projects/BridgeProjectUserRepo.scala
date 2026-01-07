package com.taskmanagement.lib.postgres.projects.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class BridgeProjectUserRepository(db: Database) {
    private val projects_users = TableQuery[BridgeProjectUserTable]

    def findById(id: Long): Future[Option[BridgeProjectUser]] = 
        db.run(projects_users.filter(_.prj_usr_id === id).result.headOption)

    def findAll(): Future[Seq[BridgeProjectUser]] = 
        db.run(projects_users.result)
    
}
