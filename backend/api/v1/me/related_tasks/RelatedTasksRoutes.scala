package com.taskmanagement.api.v1.me.related_tasks.demo1

import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server.Route

class RelatedTasksRoutes(authService: AuthService) {
    val routes: Route = pathPrefix("me" / "related_tasks") {
        concat(
            pathEnd{
                get {
                    complete("get my projects")
                }
            },

            path("kanban") {
                get {
                    complete("get my project's kanbans")
                }
            },

            path(Segment) { hashed_prj_id =>
                get {
                    complete(s"get project detail: $hashed_prj_id")
                }
            }
        )
    }
}