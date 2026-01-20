package demo1.taskmanagement.lib.tasks

// dependencies
import play.api.libs.json.{Json, OFormat}
import java.time.LocalDate
import scala.math.BigDecimal
import com.typesafe.scalalogging.LazyLogging

final case class TaskUpdateRequest(
    tsk_name: Option[String],
    tsk_description: Option[String],
    tsk_start_date: Option[LocalDate],
    tsk_due_date: Option[LocalDate],
    tsk_estimated_hours: Option[BigDecimal],
    tsk_status: Option[String],
    spr_id: Option[String]
)

object TaskUpdateRequest extends LazyLogging {
    implicit val format: OFormat[TaskUpdateRequest] = Json.format[TaskUpdateRequest]
}