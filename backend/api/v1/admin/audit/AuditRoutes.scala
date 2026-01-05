package com.taskmanagement.api.v1.admin.audit.demo1

import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server.Route

class AuditRoutes(authService: AuthService) {
    val routes: Route = pathPrefix("admin" / "audit") {
        concat(
            pathEnd{
                get {
                    complete("get audit information")
                }
            },
            path("logs") {
                get {
                    complete("get audit logs")
                }
            }
        )
    }
}