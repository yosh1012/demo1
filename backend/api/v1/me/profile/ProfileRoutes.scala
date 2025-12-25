package com.taskmanagement.api.v1.me.profile.demo1

import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server.Route

class ProfileRoutes(authService: AuthService) {
    val routes: Route = pathPrefix("me" / "profile") {
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