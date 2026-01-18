package com.taskmanagement.api.v1.me.tasks.demo1

import org.apache.pekko.http.scaladsl.server.Directives
import org.apache.pekko.http.scaladsl.server.Route

class TasksRoutes(authService: AuthService) {
    val routes: Route = pathPrefix("me" / "projects" / Segment / "tasks") { hashed_prj_id =>
        concat(
            pathEnd{
                get {
                    complete("get my project's tasks")
                } ~
                post {
                    complete("created a project task")
                }
            },

            path(Segment) { hashed_tsk_id =>
                get {
                    complete(s"get task detail: $hashed_tsk_id")
                } ~
                put {
                    complete(s"edited task settings: $hashed_tsk_id")
                } ~
                delete {
                    complete(s"deleted task: $hashed_tsk_id")
                }
            }        
        )
    }
}