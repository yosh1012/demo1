package com.taskmanagement.organizations.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future
import java.time.{LocalDate, LocalDateTime}

class OrganizationTable(tag: Tag) extends Table[Organization](tag, "organizations") {

        def org_id = column[Long]("org_id", O.PrimaryKey, O.AutoInc)

        def org_name = column[String]("org_name")
        def org_description = column[Option[String]]("org_description")
        def org_is_active = column[Boolean]("org_is_active")
        def org_current_user_count = column[Int]("org_current_user_count")
        def org_subscription_started_date = column[Option[LocalDate]]("org_subscription_started_date")
        def org_subscription_end_date = column[Option[LocalDate]]("org_subscription_end_date")
        def org_subscription_status = column[String]("org_subscription_status")
        def org_subscription_is_autorenew = column[Boolean]("org_subscription_is_autorenew")
        def org_subscription_canceled_at = column[Option[LocalDateTime]]("org_subscription_canceled_at")

        def org_created_at = column[LocalDateTime]("org_created_at")
        def org_updated_at = column[Option[LocalDateTime]]("org_updated_at")
        def org_deleted_at = column[Option[LocalDateTime]]("org_deleted_at")

        def spl_id = column[Int]("spl_id")
        def usr_id = column[Long]("usr_id")
    
    def * = (
                org_id,
                org_name,
                org_description,
                org_is_active,
                org_current_user_count,
                org_subscription_started_date,
                org_subscription_end_date,
                org_subscription_status,
                org_subscription_is_autorenew,
                org_subscription_canceled_at,
                org_created_at,
                org_updated_at,
                org_deleted_at,
                spl_id,
                usr_id
    ).mapTo[Organization]
}