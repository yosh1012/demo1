package demo1.taskmanagement.api.v1.me.projects

import org.apache.pekko.http.scaladsl.server.Directives
import org.apache.pekko.http.scaladsl.server.Route

class MeProjectsRoutes(authService: AuthService) {
    val routes: Route = pathPrefix("me" / "projects") {
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