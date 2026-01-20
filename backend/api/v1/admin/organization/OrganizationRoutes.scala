package demo1.taskmanagement.api.v1.admin.organization

import org.apache.pekko.http.scaladsl.server.Directives
import org.apache.pekko.http.scaladsl.server.Route

class OrganizationRoutes(authService: AuthService) {
    val routes: Route = pathPrefix("admin" / "organization") {
        concat(
            pathEnd{
                get {
                    complete("get organization information")
                } ~
                put {
                    complete("updated organization name")
                }
            },
            path("users") {
                get {
                    complete("get organization users")
                } ~
                post {
                    complete("created a new organization user")
                }
            },
            path("users" / Segment) { hashed_usr_id =>
                put {
                    complete("set organization admin role to user")
                } ~
                delete {
                    complete("deleted organization user")
                }
            },
            path("work_times") {
                get {
                    complete("get organization total worktimes")
                }
            }
        )
    }
}