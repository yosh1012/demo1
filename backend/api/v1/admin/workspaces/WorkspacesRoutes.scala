package com.taskmanagement.api.v1.admin.workspaces.demo1

import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server.Route

class WorkspacesRoutes(authService: AuthService) {
    val routes: Route = pathPrefix("admin" / "workspaces") {
        concat(
            pathEnd{
                get {
                    complete("get workspace information")
                } ~
                post {
                    complete("create a new workspace")
                }
            },
            path(Segment / "work_times") { hashed_wks_id =>
                get {
                    complete("get workspace's total worktimes")
                }
            },
            path(Segment / "users" / Segment) { (hashed_wks_id, hashed_usr_id) =>
                put {
                    complete("set workspace manager to user")
                } ~
                delete {
                    complete("delete user from workspace")
                }
            },
            path(Segment) { hashed_wks_id =>
                put {
                    complete("edited workspace settings")
                } ~
                delete {
                    complete("deleted workspace")
                }
            }
        )
    }
}