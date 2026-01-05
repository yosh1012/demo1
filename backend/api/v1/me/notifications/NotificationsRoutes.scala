package com.taskmanagement.api.v1.me.notifications.demo1

import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server.Route

class NotificationsRoutes(authService: AuthService) {
    val routes: Route = pathPrefix("me" / "notifications") {
        concat(
            path("notifications") {
                get {
                    complete("show my notifications")
                }
            }
        )
    }
}