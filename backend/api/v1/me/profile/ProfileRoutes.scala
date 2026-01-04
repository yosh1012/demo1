package com.taskmanagement.api.v1.me.profile.demo1

import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server.Route

class ProfileRoutes(authService: AuthService) {
    val routes: Route = pathPrefix("me" / "profile") {
        concat(
            pathEnd{
                get {
                    complete("get user profile")
                }
                put {
                    complete("update user profile")
                }
            },
            
            path("email") {
                put {
                    complete("update user email")
                }
            },

            path("password") {
                post {
                    complete("reset user password")
                }
            }
        )
    }
}