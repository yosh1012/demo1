package com.taskmanagement.lib.auth.demo1

// dependencies
import play.api.libs.json.{Json, OFormat}

final case class RefreshTokenRequest (
    refresh_token: String
)

object RefreshTokenRequest {
    implicit val format: OFormat[RefreshTokenRequest] = Json.format[RefreshTokenRequest]
}