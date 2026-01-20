package demo1.taskmanagement.api.v1.me.notifications

import org.apache.pekko.http.scaladsl.server.Directives
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