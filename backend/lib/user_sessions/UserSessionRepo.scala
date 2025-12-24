package com.taskmanagement.user_sessions.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class UserSessionTable(tag: Tag) extends Table[UserSession](tag, "user_sessions") {
    def column_name = column[Type]("column_name", O.PrimaryKey, O.AutoInc)

    def ssn_created_at = column[java.time.LocalDateTime]("ssn_created_at") // DEFAULT CURRENT_TIMESTAMP
    def ssn_updated_at = column[Option[java.time.LocalDateTime]]("ssn_updated_at")
    def ssn_deleted_at = column[Option[java.time.LocalDateTime]]("ssn_deleted_at")

    def * = (
        column_name1, column_name2, column_name3, column_name4,
        column_name5, column_name6, column_name7
    ).mapTo[UserSession]
}

class UserSessionRepository(db: Database) {
    private val user_sessions = TableQuery[UserSessionTable]

    def findById(id: Long): Future[Option[UserSession]] = 
        db.run(user_sessions.filter(_.ssn_id === id).result.headOption)

    def findAll(): Future[Seq[UserSession]] = 
        db.run(user_sessions.result)
    
}
