package com.taskmanagement.api.v1.me.tasks.demo1

import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server.Route

class TasksRoutes(authService: AuthService) {
    val routes: Route = pathPrefix("me" / "tasks") {
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