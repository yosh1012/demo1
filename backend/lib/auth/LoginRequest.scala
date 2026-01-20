package demo1.taskmanagement.lib.auth

// dependencies
import play.api.libs.json.{Json, OFormat}
import com.typesafe.scalalogging.LazyLogging

final case class LoginRequest (
    email: String,
    password: String
)

object LoginRequest extends LazyLogging {
    implicit val format: OFormat[LoginRequest] = Json.format[LoginRequest]
}