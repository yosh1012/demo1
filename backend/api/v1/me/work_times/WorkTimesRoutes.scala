package com.taskmanagement.api.v1.me.work_times.demo1

import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server.Route

class WorkTimesRoutes(authService: AuthService) {
    val routes: Route = pathPrefix("me" / "work_times") {
        concat(
            pathEnd{
                get {
                    complete("get my worktimes")
                } ~
                post {
                    complete("created my worktime record")
                } ~
                delete {
                    complete("deleted my worktime record")
                }
            }   
        )
    }
}