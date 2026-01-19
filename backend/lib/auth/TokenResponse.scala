package com.taskmanagement.lib.auth.demo1

// dependencies
import play.api.libs.json.{Json, OFormat}
import com.taskmanagement.lib.postgres.users.demo1.UserDTO
import com.typesafe.scalalogging.LazyLogging

final case class TokenResponse(
    access_token: String,
    refresh_token: String,
    token_type: String, // = "Bearer",
    expires_in: Long,
    user: UserDTO
)

object TokenResponse extends LazyLogging {
    implicit val format: OFormat[TokenResponse] = Json.format[TokenResponse]
}