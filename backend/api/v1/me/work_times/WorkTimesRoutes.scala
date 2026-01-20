package demo1.taskmanagement.api.v1.me.work_times

import org.apache.pekko.http.scaladsl.server.Directives
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