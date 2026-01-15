package com.taskmanagement.lib.postgres.organizations.demo1

import play.api.libs.json.{Json, OFormat}
import java.time.{LocalDateTime, LocalDate}

case class OrganizationDTO (
        org_id: Long, // PK

        org_name: String,
        org_description: Option[String],
        org_is_active: Boolean, // DEFAULT false
        org_current_user_count: Int, // DEFAULT 1,
        org_subscription_started_date: Option[LocalDate],
        org_subscription_end_date: Option[LocalDate],
        org_subscription_status: String, // ENUM: SUBSCRIPTION_STATUS
        org_subscription_is_autorenew: Boolean, // DEFAULT true,
        org_subscription_canceled_at: Option[LocalDateTime],

        org_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP,
        org_updated_at: Option[LocalDateTime],
        org_deleted_at: Option[LocalDateTime],
        
        spl_id: Int, // REFERENCES subscription_plans(spl_id) ON DELETE CASCADE,
        usr_id: Long // REFERENCES users(usr_id) ON DELETE CASCADE
)

object OrganizationDTO {
    implicit val format: OFormat[OrganizationDTO] = Json.format[OrganizationDTO]
}
