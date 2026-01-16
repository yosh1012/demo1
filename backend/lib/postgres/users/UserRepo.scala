package com.taskmanagement.lib.postgres.users.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.{Future, ExecutionContext}

class UserRepository(db: Database)(implicit ec: ExecutionContext) {
    private val users = TableQuery[UserTable]

    /**
     * get user entity by id
     * @param id
     * @return user entity
     */
    def findById(id: Long): Future[Option[User]] = 
        db.run(users.filter(_.usr_id === id).result.headOption)

    /**
     * get user entity by email
     * @param email
     * @return user entity
     */
    def findByEmail(email: String): Future[Option[User]] =
        db.run(users.filter(_.usr_email === email).result.headOption)

    /**
     * get all user entity list
     * @return user entity list
     */
    def findAll(): Future[Seq[User]] = 
        db.run(users.result)

    /**
     * create user
     * @param userEntity
     * @return return a record from DB (information of the result of INSERT)
     */
    def create(userEntity: User): Future[User] = {
        val insertQuery =
            users
                .returning(users.map(_.usr_id))
                .into((user, id) => user.copy(usr_id = id)) // lambda with slick syntax
        db.run(insertQuery += userEntity)
    }
    
}
