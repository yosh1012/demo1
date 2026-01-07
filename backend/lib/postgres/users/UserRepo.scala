package com.taskmanagement.lib.postgres.users.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class UserRepository(db: Database) {
    private val users = TableQuery[UserTable]

    def findById(id: Long): Future[Option[User]] = 
        db.run(users.filter(_.usr_id === id).result.headOption)

    def findByEmail(email: String): Future[Option[User]] =
        db.run(users.filter(_.usr_email === email).result.headOption)

    def findAll(): Future[Seq[User]] = 
        db.run(users.result)
    
}
