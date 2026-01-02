package com.taskmanagement.subscription_plans.demo1

import play.api.libs.json.{Json, OFormat}
import java.time.LocalDateTime

case class SubscriptionPlan(
    spl_id: Int, // PK
    spl_can_add_users: Boolean, // DEFALUT false
    spl_included_users_count: Int, // DEFAULT 1
    spl_max_projects_count: Int, // DEFAULT 1
    spl_max_workspaces_count: Int, // DEFAULT 1
    spl_has_ads: Boolean, // DEFAULT false
    spl_can_add_opened_workspace: Boolean, // DEFAULT false
    
    spl_regular_price: Decimal,
    spl_discount_3months: Option[Decimal], // DEFAULT 0.00
    spl_discount_6months: Option[Decimal], // DEFAULT 0.00
    spl_discount_1year: Option[Decimal], // DEFAULT 0.00
    spl_3months_monthly_price: Option[Decimal],
    spl_6months_monthly_price: Option[Decimal],
    spl_1year_monthly_price: Option[Decimal],
    spl_additional_user_price: Option[Decimal] // DEFAULT 0.00

    spl_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP
    spl_updated_at: Option[LocalDateTime],
    spl_deleted_at: Option[LocalDateTime]
)

object SubscriptionPlan {
    implicit val format: OFormat[SubscriptionPlan] = Json.format[SubscriptionPlan]
}