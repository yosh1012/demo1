package com.taskmanagement.lib.http.demo1

import com.typesafe.config.ConfigFactory
import org.apache.pekko.http.scaladsl.model.{HttpMethods => HM}._
import org.apache.pekko.http.scaladsl.model.headers._
import org.apache.pekko.http.scaladsl.model.{HttpResponse => HR, StatusCodes => SC}
import org.apache.pekko.http.scaladsl.server.{Directives => D}._
import org.apache.pekko.http.scaladsl.server.{Directive0 => D0, Route => R}
import scala.jdk.CollectionConverters._

object CorsHandler{

    private val config = ConfigFactory.load()

    private val allowedOrigins: Set[String] = 
        config.getStringList("cors.allowed-origins").asScala.toSet

    private val allowedMethods: Seq[HM.HttpMethod] = Seq(GET, POST, PUT, DELETE, OPTIONS)

    private val allowedHeaders: Seq[String] = Seq("Authorization", "Content-Type")

    private def corsResponseHeaders(originUrl: String): Directive0 = {
        D.respondWithHeaders(
            `Access-Control-Allow-Origin`(HttpOrigin(originUrl)),
            `Access-Control-Allow-Methods`(allowedMethods),
            `Access-Control-Allow-Headers`(allowedHeaders),
            `Access-Control-Allow-Credentials`(true)
        )
    }

    def withCors(apiRoute: Route): Route = {
        // pekko.http.scaladsl.model.headers.Origin
        D.optionalHeaderValueByType[Origin](()) {
            case Some(originHeader: Origin) =>
                val originUrl: String = originHeader.value

                if(allowedOrigins.contains(originUrl)) {
                    D.options {
                        corsResponseHeaders(originUrl) {
                            D.complete(HttpResponse(StatusCodes.OK))
                        }
                    } ~
                    corsResponseHeaders(originUrl) {
                        apiRoute
                    }
                } else {
                    D.complete(HttpResponse(StatusCodes.Forbidden))
                }
                
            case None =>
                apiRoute
        }
    }
}