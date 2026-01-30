package demo1.taskmanagement.main

import slick.jdbc.PostgresProfile.api._
import com.typesafe.config.{ConfigFactory, Config}
import com.typesafe.scalalogging.LazyLogging
import scala.concurrent.{Await, ExecutionContext}
import scala.util.{Success, Failure}
import scala.concurrent.duration._ // wildcard

import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.http.scaladsl.Http
import org.apache.pekko.http.scaladsl.server.Route
import org.apache.pekko.http.scaladsl.server.Directives

import demo1.taskmanagement.lib.http.CorsHandler
import demo1.taskmanagement.lib.postgres.users.UserRepository
import demo1.taskmanagement.lib.postgres.tasks.TaskRepository
import demo1.taskmanagement.api.v1.auth.AuthService
import demo1.taskmanagement.api.v1.auth.AuthRoutes
import demo1.taskmanagement.api.v1.health.HealthRoutes
import demo1.taskmanagement.api.v1.me.tasks.TasksService
import demo1.taskmanagement.api.v1.me.tasks.TasksRoutes

object Main extends App with LazyLogging {

    // System.setProperty("java.net.preferIPv4Stack", "true") // to use IPv4 // commented out because fly.io only supports ipv6

    val config: Config = ConfigFactory.load()

    // config debug
    val jdbcUrl = config.getString("demo1-db.db.jdbcUrl")
    println(s"DEBUG: jdbcUrl = $jdbcUrl")

    val httpInterface: String = config.getString("http.interface")
    val httpPort: Int = config.getInt("http.port")

    implicit val actorSystem: ActorSystem = ActorSystem("tsumiki-api")
    implicit val executionContext: ExecutionContext = actorSystem.dispatcher

    // config debug 
    /*
    println("config debug")
    println(s"demo1-db: ${applicationConfig.hasPath("demo1-db")}")
    println(s"demo1-db.db: ${applicationConfig.hasPath("demo1-db.db")}")
    println(s"demo1-db.db.jdbcUrl: ${applicationConfig.hasPath("demo1-db.db.jdbcUrl")}")
    println(s"jdbcUrl: ${applicationConfig.getString("demo1-db.db.jdbcUrl")}")
    println("end debug")
    */

    val dbConnectionPool: Database = Database.forConfig("demo1-db.db", config)
    val userRepo: UserRepository = new UserRepository(dbConnectionPool)
    val taskRepo: TaskRepository = new TaskRepository(dbConnectionPool)
    val authService: AuthService = new AuthService(userRepo)
    val tasksService: TasksService = new TasksService(taskRepo)
    val authRoutes: AuthRoutes = new AuthRoutes(authService)
    val tasksRoutes: TasksRoutes = new TasksRoutes(tasksService)
    val healthRoutes: HealthRoutes = new HealthRoutes()

    println(s"prj_id=1 hash: ${demo1.taskmanagement.lib.hashids.HashidsHandler.encode(1L)}")


    // integrate all routes
    val allRoutes: Route = Directives.concat(
        healthRoutes.routes,
        authRoutes.routes,
        tasksRoutes.routes
    )

    val corsWrappedRoutes: Route = CorsHandler.wrapWithCors(allRoutes)
    val serverBinding = Http().newServerAt(httpInterface, httpPort).bind(corsWrappedRoutes)

    serverBinding.onComplete {
        case Success(serverBinding) =>
            val address = serverBinding.localAddress
            logger.info(s"Server started at http://${address.getHostString}:${address.getPort}")
        
        case Failure(ex) =>
            logger.error(s"Failed to bind HTTP server: ${ex.getMessage}")
            actorSystem.terminate()
    }

    // shutdown
    sys.addShutdownHook {
        serverBinding.flatMap(_.unbind()).onComplete { _ =>
            dbConnectionPool.close()
            actorSystem.terminate()
        }
    }
    
    // connecting test starts
    //println("Connecting to database...")
    //val connectionTestQuery = sql"SELECT 1".as[Int]
    //val connectionTestResult = Await.result(dbConnectionPool.run(connectionTestQuery), 10.seconds)
    //println(s"Connection successful: $connectionTestResult")
    //dbConnectionPool.close()
    // connecting test ends
}