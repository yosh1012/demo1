package com.taskmanagement.api.v1.me.sprints.demo1

import org.apache.pekko.http.scaladsl.server.Directives
import org.apache.pekko.http.scaladsl.server.Route

class SprintsRoutes(authService: AuthService) {
    val routes: Route = pathPrefix("me" / "projects" / Segment / "sprints") { hashed_prj_id =>
        concat(
            pathEnd{
                post {
                    complete("created a new sprint")
                }
            },

            path("ganttchart") {
                get {
                    complete("get sprint's gannt chart")
                }
            },

            path(Segment) { hashed_spr_id =>
                get {
                    complete(s"get sprint detail: $hashed_spr_id")
                } ~
                put {
                    complete(s"edited sprint settings: $hashed_spr_id")
                } ~
                delete {
                    complete(s"deleted sprint: $hashed_spr_id")
                }
            }        
        )
    }
}