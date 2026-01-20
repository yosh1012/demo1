package demo1.taskmanagement.api.v1.health

// dependencies
import org.apache.pekko.http.scaladsl.server.{Directives => D}
import org.apache.pekko.http.scaladsl.server.Route
import org.apache.pekko.http.scaladsl.model.StatusCodes

class HealthRoutes {
    val routes: Route = D.path("health") {
        D.get {
            D.complete(StatusCodes.OK, "OK")
        }
    }
}