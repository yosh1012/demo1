package demo1.taskmanagement.lib.postgres.users

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

final case class UserDTO(
    usr_id: Long, // PK
    usr_email: String,
    usr_first_name: Option[String],
    usr_last_name: Option[String],
    usr_display_name: Option[String],
    usr_profile_image_url: Option[String],
    usr_timezone: Option[String],
    usr_lang_preference: Option[String],
    usr_last_login_at: Option[LocalDateTime],
    usr_is_active: Boolean,
    usr_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP
    usr_updated_at: Option[LocalDateTime],
    usr_deleted_at: Option[LocalDateTime]
)

object UserDTO {
    implicit val format: OFormat[UserDTO] = Json.format[UserDTO]

    /**
     * create user DTO entity
     * @param user
     * @return user DTO entity
     */
    def createDTO(user: User): UserDTO = {
        UserDTO(
            usr_id = user.usr_id,
            usr_email = user.usr_email,
            usr_first_name = user.usr_first_name,
            usr_last_name = user.usr_last_name,
            usr_display_name = user.usr_display_name,
            usr_profile_image_url = user.usr_profile_image_url,
            usr_timezone = user.usr_timezone,
            usr_lang_preference = user.usr_lang_preference,
            usr_last_login_at = user.usr_last_login_at,
            usr_is_active = user.usr_is_active,
            usr_created_at = user.usr_created_at,
            usr_updated_at = user.usr_updated_at,
            usr_deleted_at = user.usr_deleted_at
        )
    }
}