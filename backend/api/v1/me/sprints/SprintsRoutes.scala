package com.taskmanagement.api.v1.me.sprints.demo1

import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server.Route

class SprintsRoutes(authService: AuthService) {
    val routes: Route = pathPrefix("me" / "sprints") {
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