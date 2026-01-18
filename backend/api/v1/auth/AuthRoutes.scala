package com.taskmanagement.api.v1.auth.demo1

// dependencies
import org.apache.pekko.http.scaladsl.server.{Directives => D}
import org.apache.pekko.http.scaladsl.server.Route
import org.apache.pekko.http.scaladsl.model.StatusCodes
import com.github.pjfanning.pekkohttpplayjson.PlayJsonSupport
import scala.util.{Success, Failure}
import com.taskmanagement.lib.auth.demo1.{SignupRequest, LoginRequest, TokenResponse}

class AuthRoutes(authService: AuthService) extends PlayJsonSupport {
    val routes: Route = D.pathPrefix("auth") {
        D.concat(
            D.path("signup") {
                D.post {
                    D.entity(D.as[SignupRequest]) {(signupRequestData: SignupRequest) =>
                        D.onComplete(authService.signup(signupRequestData)) {
                            case Success(tokenResponseData: TokenResponse) =>
                                D.complete(StatusCodes.Created, tokenResponseData) // 201

                            case Failure(ex: IllegalArgumentException) =>
                                D.complete(StatusCodes.BadRequest, ex.getMessage) // 400
                            
                            case Failure(ex: Throwable) =>
                                D.complete(StatusCodes.InternalServerError, "Internal server error") // 500
                        }
                    }
                }
            },
            D.path("password") {
                D.post {
                    // TODO
                    }
                }
            },
            D.path("login") {
                D.post {
                    D.onComplete(D.as[LoginRequest]) { (loginRequestData: LoginRequest) =>
                        case Success(tokenResponseData: TokenResponse) =>
                            D.complete(StatusCodes.OK, tokenResponseData) // 200
                        
                        case Failure(ex: IllegalArgumentException) =>
                            D.complete(StatusCodes.Unauthorized, ex.getMessage) // 401

                        case Failure(ex: Throwable) ->
                            D.complete(StatusCodes.InternalServerError, "Internal server error") // 500
                }
            },
            D.path("verify") {
                D.get {
                    AuthHandler.authenticate { (usr_id: Long) =>
                        D.complete(StatusCodes.OK, s"{usr_id: $usr_id}") //200
                    }
                }
            },
            D.path("refresh") {
                D.post {
                    D.complete("refresh user's access token") // TODO
                }
            },
            D.path("logout") {
                D.post {
                    D.complete(StatusCodes.OK, "Logged out")
                }
            }
        )
    }
}