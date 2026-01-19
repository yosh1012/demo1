package com.taskmanagement.lib.auth.demo1

// dependencies
import play.api.libs.json.{Json, OFormat}
import java.time.LocalDateTime
import com.taskmanagement.lib.postgres.users.demo1.User
import com.typesafe.scalalogging.LazyLogging

final case class SignupRequest extends LazyLogging (
    email: String,
    password: String,
    display_name: Option[String]
)

object SignupRequest {
    implicit val format: OFormat[SignupRequest] = Json.format[SignupRequest]

    /**
     * convert SignupRequest to User model
     * @param signupRequestData
     * @param hashedPassword
     * @return user model
     */
    def createModel(signupRequestData: SignupRequest, hashedPassword: String): User = {
        val currentTime: LocalDateTime = LocalDateTime.now()
        User( // constructor
            usr_id = 0L, // will be replaced with the number from DB auto-increment
            usr_email = signupRequestData.email,
            usr_hashed_password = hashedPassword,
            usr_display_name = signupRequestData.display_name,
            usr_first_name = None,
            usr_last_name = None,
            usr_profile_image_url = None,
            usr_timezone = None,
            usr_lang_preference = None,
            usr_last_login_at = Some(currentTime),
            usr_is_active = true,
            usr_created_at = currentTime,
            usr_updated_at = None,
            usr_deleted_at = None
        )
    }
    
}