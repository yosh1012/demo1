package com.taskmanagement.lib.postgres.user_sessions.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future
import java.time.{LocalDate, LocalDateTime}
import java.util.UUID  

class UserSessionTable(tag: Tag) extends Table[UserSession](tag, "user_sessions") {
        def ssn_id = column[UUID]("ssn_id")

        def ssn_jwt_refresh_hashed_token = column[String]("ssn_jwt_refresh_hashed_token")

        def ssn_usr_agent = column[Option[String]]("ssn_usr_agent")
        def ssn_ip_address = column[Option[String]]("ssn_ip_address")
        def ssn_will_expire_at = column[LocalDateTime]("ssn_will_expire_at")
        def ssn_last_accessed_at = column[LocalDateTime]("ssn_last_accessed_at")

        def ssn_created_at = column[LocalDateTime]("ssn_created_at")
        def ssn_updated_at = column[Option[LocalDateTime]]("ssn_updated_at")

        def usr_id = column[Long]("usr_id")

    def * = (
                ssn_id,
                ssn_jwt_refresh_hashed_token,
                ssn_usr_agent,
                ssn_ip_address,
                ssn_will_expire_at,
                ssn_last_accessed_at,
                ssn_created_at,
                ssn_updated_at,
                usr_id
    ).mapTo[UserSession]
}
