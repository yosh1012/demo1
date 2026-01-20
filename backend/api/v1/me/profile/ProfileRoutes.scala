package demo1.taskmanagement.api.v1.me.profile

import org.apache.pekko.http.scaladsl.server.Directives
import org.apache.pekko.http.scaladsl.server.Route

class ProfileRoutes(authService: AuthService) {
    val routes: Route = pathPrefix("me" / "notifications") {
        concat(
            pathEnd{
                get {
                    complete("get my notifications")
                }
            }
        )
    }
}