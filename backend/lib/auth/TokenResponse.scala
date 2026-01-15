package com.taskmanagement.lib.auth.demo1

// dependencies
import play.api.libs.json.{Json, OFormat}
import com.taskmanagement.lib.postgres.users.demo1.UserDTO

final case class TokenResponse(
    access_token: String,
    token_type: String // = "Bearer",
    expires_in: Long,
    user: UserDTO
)

object TokenResponse {
    implicit val format: Oformat[TokenResponse] = Json.format[TokenResponse]
}