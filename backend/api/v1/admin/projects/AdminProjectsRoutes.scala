package demo1.taskmanagement.api.v1.admin.projects

import org.apache.pekko.http.scaladsl.server.Directives
import org.apache.pekko.http.scaladsl.server.Route

class AdminProjectsRoutes(authService: AuthService) {
    val routes: Route = pathPrefix("admin" / "projects") {
        concat(
            pathEnd{
                get {
                    complete("get project information")
                } ~
                post {
                    complete("create a new project")
                }
            },
            path(Segment / "work_times") { hashed_prj_id =>
                get {
                    complete("get project's total worktimes")
                }
            },
            path(Segment / "users" / Segment) { (hashed_prj_id, hashed_usr_id) =>
                put {
                    complete("set project role to user")
                } ~
                delete {
                    complete("delete user from project")
                }
            },
            path(Segment) { hashed_prj_id =>
                put {
                    complete("edited project settings")
                } ~
                delete {
                    complete("deleted project")
                }
            }
        )
    }
}