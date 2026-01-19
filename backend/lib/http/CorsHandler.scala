package com.taskmanagement.lib.http.demo1

import com.typesafe.config.ConfigFactory
import org.apache.pekko.http.scaladsl.model.headers._
import org.apache.pekko.http.scaladsl.model.HttpMethods._ // GET POST PUT DELETE etc
import org.apache.pekko.http.scaladsl.model.{HttpMethod, HttpResponse, StatusCodes}
import org.apache.pekko.http.scaladsl.server.{Directives, Directive0, Route}
import scala.jdk.CollectionConverters._ // wildcard
import com.typesafe.scalalogging.LazyLogging

object CorsHandler extends LazyLogging {

    private val config = ConfigFactory.load()
    private val allowedOrigins: Set[String] = 
        config.getStringList("cors.allowed-origins").asScala.toSet
    private val allowedMethods: Seq[HttpMethod] = Seq(GET, POST, PUT, DELETE, OPTIONS)
    private val allowedHeaders: Seq[String] = Seq("Authorization", "Content-Type")

    /**
     * add CORS header
     * @param originUrl
     * @return 
     */
    private def addCorsResponseHeaders(originUrl: String): Directive0 = {
        Directives.respondWithHeaders(
            `Access-Control-Allow-Origin`(HttpOrigin(originUrl)),
            `Access-Control-Allow-Methods`(allowedMethods),
            `Access-Control-Allow-Headers`(allowedHeaders),
            `Access-Control-Allow-Credentials`(true)
        )
    }

    /**
     * wrap a current Route with CORS header
     * @param apiRoute
     * @return a new Route with CORS header
     */
    def wrapWithCors(apiRoute: Route): Route = {
        // pekko.http.scaladsl.model.headers.Origin
        Directives.optionalHeaderValueByType[Origin](()) {
            case Some(originHeader: Origin) =>
                val originUrl: String = originHeader.value

                if(allowedOrigins.contains(originUrl)) {
                    Directives.options {
                        addCorsResponseHeaders(originUrl) {
                            Directives.complete(HttpResponse(StatusCodes.OK))
                        }
                    }
                    addCorsResponseHeaders(originUrl) {
                        apiRoute
                    }
                } else {
                    Directives.complete(HttpResponse(StatusCodes.Forbidden))
                }
                
            case None =>
                apiRoute
        }
    }
}