package com.taskmanagement.main.demo1

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

import com.taskmanagement.lib.http.demo1.CorsHandler
import com.taskmanagement.lib.postgres.users.demo1.UserRepository
import com.taskmanagement.api.v1.auth.demo1.AuthService
import com.taskmanagement.api.v1.auth.demo1.AuthRoutes
import com.taskmanagement.api.v1.health.demo1.HealthRoutes

object Main extends App with LazyLogging {

    System.setProperty("java.net.preferIPv4Stack", "true") // to use IPv4

    val config: Config = ConfigFactory.load()
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
    val authService: AuthService = new AuthService(userRepo)
    val authRoutes: AuthRoutes = new AuthRoutes(authService)
    val healthRoutes: HealthRoutes = new HealthRoutes()

    // integrate all routes
    val allRoutes: Route = Directives.concat(
        healthRoutes.routes,
        authRoutes.routes
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