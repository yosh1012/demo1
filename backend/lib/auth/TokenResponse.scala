package demo1.taskmanagement.lib.auth

// dependencies
import play.api.libs.json.{Json, OFormat}
import demo1.taskmanagement.lib.postgres.users.UserDTO
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