package com.taskmanagement.user_sessions.demo1

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}
import java.util.UUID  

case class UserSession(
        ssn_id: UUID, // PK

        ssn_jwt_refresh_hashed_token: String,

        ssn_usr_agent: Option[String],
        ssn_ip_address: Option[String],
        ssn_will_expire_at: LocalDateTime,
        ssn_last_accessed_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP
        
        ssn_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP
        ssn_updated_at: Option[LocalDateTime],
        // Physical Delete

        usr_id: Long // REFERENCES users(usr_id) ON DELETE CASCADE
)

object UserSession {
    implicit val format: OFormat[UserSession] = Json.format[UserSession]
}