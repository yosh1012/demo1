package com.taskmanagement.lib.postgres.users.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future
import java.time.LocalDateTime

class UserTable(tag: Tag) extends Table[User](tag, "users") {
    def usr_id = column[Long]("usr_id", O.PrimaryKey, O.AutoInc)
    def usr_email = column[String]("usr_email")
    def usr_first_name = column[Option[String]]("usr_first_name")
    def usr_last_name = column[Option[String]]("usr_last_name")
    def usr_display_name = column[Option[String]]("usr_display_name")
    def usr_hashed_password = column[String]("usr_hashed_password")
    def usr_profile_image_url = column[Option[String]]("usr_profile_image_url")
    def usr_timezone = column[Option[String]]("usr_timezone")
    def usr_lang_preference = column[Option[String]]("usr_lang_preference")
    def usr_last_login_at = column[Option[LocalDateTime]]("usr_last_login_at")
    def usr_is_active = column[Boolean]("usr_is_active")
    def usr_created_at = column[LocalDateTime]("usr_created_at")
    def usr_updated_at = column[Option[LocalDateTime]]("usr_updated_at")
    def usr_deleted_at = column[Option[LocalDateTime]]("usr_deleted_at")

    def * = (
        usr_id, usr_email, usr_first_name, usr_last_name,
        usr_display_name, usr_hashed_password, usr_profile_image_url, usr_timezone,
        usr_lang_preference, usr_last_login_at, usr_is_active,
        usr_created_at, usr_updated_at, usr_deleted_at
    ).mapTo[User]
}