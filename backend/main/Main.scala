package com.taskmanagement.main.demo1

import slick.jdbc.PostgresProfile.api._
import com.typesafe.config.ConfigFactory
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext

object Main extends App {

    // implicit Execution Context as global one, runs for every process
    implicit val executionContext: ExecutionContext = scala.concurrent.ExecutionContext.global

    val applicationConfig = ConfigFactory.load()

    // config debug 
    /*
    println("config debug")
    println(s"demo1-db: ${applicationConfig.hasPath("demo1-db")}")
    println(s"demo1-db.db: ${applicationConfig.hasPath("demo1-db.db")}")
    println(s"demo1-db.db.jdbcUrl: ${applicationConfig.hasPath("demo1-db.db.jdbcUrl")}")
    println(s"jdbcUrl: ${applicationConfig.getString("demo1-db.db.jdbcUrl")}")
    println("end debug")
    */

    val dbConnectionPool = Database.forConfig("demo1-db.db", applicationConfig)
    
    // connecting test starts
    println("Connecting to database...")

    val connectionTestQuery = sql"SELECT 1".as[Int]

    val connectionTestResult = Await.result(dbConnectionPool.run(connectionTestQuery), 10.seconds)

    println(s"Connection successful: $connectionTestResult")

    dbConnectionPool.close()
    // connecting test ends
}