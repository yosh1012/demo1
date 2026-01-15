package com.taskmanagement.lib.postgres.users.demo1

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDate, LocalDateTime}

case class User(
    usr_id: Long, // PK
    usr_email: String,
    usr_first_name: Option[String],
    usr_last_name: Option[String],
    usr_display_name: Option[String],
    usr_hashed_password: String,
    usr_profile_image_url: Option[String],
    usr_timezone: Option[String],
    usr_lang_preference: Option[String],
    usr_last_login_at: Option[LocalDateTime],
    usr_is_active: Boolean,
    usr_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP
    usr_updated_at: Option[LocalDateTime],
    usr_deleted_at: Option[LocalDateTime]
)

object User {
    implicit val format: OFormat[User] = Json.format[User]
}