package demo1.taskmanagement.lib.postgres.user_sessions

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}
import java.util.UUID  

final case class UserSessionDTO (
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

object UserSessionDTO {
    implicit val format: OFormat[UserSessionDTO] = Json.format[UserSessionDTO]

    /**
     * create user_session DTO entity
     * @param user_session
     * @return user_session DTO entity
     */
    def create(user_session: UserSession): UserSessionDTO = {
        UserSessionDTO(
            ssn_id = user_session.ssn_id,
            ssn_jwt_refresh_hashed_token = user_session.ssn_jwt_refresh_hashed_token,
            ssn_usr_agent = user_session.ssn_usr_agent,
            ssn_ip_address = user_session.ssn_ip_address,
            ssn_will_expire_at = user_session.ssn_will_expire_at,
            ssn_last_accessed_at = user_session.ssn_last_accessed_at,
            ssn_created_at = user_session.ssn_created_at,
            ssn_updated_at = user_session.ssn_updated_at,
            usr_id = user_session.usr_id
        )
    }
}