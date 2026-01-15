package com.taskmanagement.api.v1.auth.demo1

// dependencies
import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server.Route

class AuthRoutes(authService: AuthService) {
    val routes: Route = pathPrefix("auth") {
        concat(
            path("signup") {
                post {
                    complete("sign up a new user")
                }
            },
            path("password") {
                post {
                    complete("reset user's password")
                }
            },
            path("login") {
                post {
                    complete("login user")
                }
            },
            path("verify") {
                get {
                    complete("verify user's access token")
                }
            },
            path("refresh") {
                post {
                    complete("refresh user's access token")
                }
            },
            path("logout") {
                post {
                    complete("logout user")
                }
            }
        )
    }
}