package com.taskmanagement.api.v1.me.related_tasks.demo1

import org.apache.pekko.http.scaladsl.server.Directives
import org.apache.pekko.http.scaladsl.server.Route

class RelatedTasksRoutes(authService: AuthService) {
    val routes: Route = pathPrefix("me" / "related_tasks") {
        concat(
            pathEnd{
                get {
                    complete("get my related tasks")
                }
            }     
        )
    }
}