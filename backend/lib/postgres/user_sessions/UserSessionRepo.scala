package com.taskmanagement.lib.postgres.user_sessions.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future
import java.util.UUID  

class UserSessionRepository(db: Database) {
    private val user_sessions = TableQuery[UserSessionTable]

    def findById(id: UUID): Future[Option[UserSession]] = 
        db.run(user_sessions.filter(_.ssn_id === id).result.headOption)

    def findAll(): Future[Seq[UserSession]] = 
        db.run(user_sessions.result)
    
}
