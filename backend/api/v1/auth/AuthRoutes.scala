package com.taskmanagement.api.v1.auth.demo1

import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server.Route

class AuthRoutes(authService: AuthService) {
    val routes: Route = pathPrefix("auth") {
        concat(
            path("endpoint1") {
                get/post {
                    complete("endpoint1")
                }
            },

            path("endpoint2") {
                get/post {
                    complete("endpoint2")
                }
            }
        )
    }
}