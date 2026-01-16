package com.taskmanagement.lib.auth.demo1

// dependencies
import play.api.libs.json.{Json, OFormat}

final case class SignupRequest (
    email: String,
    password: String,
    display_name: Option[String]
)

object SignupRequest {
    implicit val format: OFormat[SignupRequest] = Json.format[SignupRequest]
}