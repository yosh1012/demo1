package demo1.taskmanagement.lib.auth

// dependencies
import play.api.libs.json.{Json, OFormat}
import com.typesafe.scalalogging.LazyLogging

final case class RefreshTokenRequest (
    refresh_token: String
)

object RefreshTokenRequest extends LazyLogging {
    implicit val format: OFormat[RefreshTokenRequest] = Json.format[RefreshTokenRequest]
}