package com.taskmanagement.lib.http.demo1

import org.apache.pekko.http.scaladsl.marshalling.{Marshaller, ToEntityMarshaller}
import org.apache.pekko.http.scaladsl.model.{HttpEntity, MediaTypes}
import org.apache.pekko.http.scaladsl.unmarshalling.{FromEntityUnmarshaller, Unmarshaller}
import play.api.libs.json._
import com.typesafe.scalalogging.LazyLogging

trait JsonSupport extends LazyLogging {
    implicit def playJsonUnmarshaller[TargetClass](implicit reader: Reads[TargetClass]): FromEntityUnmarshaller[TargetClass] =
        Unmarshaller.byteStringUnmarshaller
        .forContentTypes(MediaTypes.`application/json`)
        .map { data =>
            val json = Json.parse(data.utf8String)
            reader.reads(json) match {
                case JsSuccess(obj, _) => obj
                case JsError(errors) => throw new IllegalArgumentException(s"JSON validation error: $errors")
            }
        }

    implicit def playJsonMarshaller[TargetClass](implicit writer: Writes[TargetClass]): ToEntityMarshaller[TargetClass] =
        Marshaller.withFixedContentType(MediaTypes.`application/json`) {obj =>
            HttpEntity(MediaTypes.`application/json`, Json.stringify(writer.writes(obj)))
        }
}
