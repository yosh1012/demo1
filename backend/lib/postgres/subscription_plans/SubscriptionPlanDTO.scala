package demo1.taskmanagement.lib.postgres.subscription_plans

import play.api.libs.json.{Json, OFormat}
import java.time.LocalDateTime

final case class SubscriptionPlanDTO (
    spl_id: Int, // PK
    spl_can_add_users: Boolean, // DEFALUT false
    spl_included_users_count: Int, // DEFAULT 1
    spl_max_projects_count: Int, // DEFAULT 1
    spl_max_workspaces_count: Int, // DEFAULT 1
    spl_has_ads: Boolean, // DEFAULT false
    spl_can_add_opened_workspace: Boolean, // DEFAULT false
    
    spl_regular_price: BigDecimal,
    spl_discount_3months: Option[BigDecimal], // DEFAULT 0.00
    spl_discount_6months: Option[BigDecimal], // DEFAULT 0.00
    spl_discount_1year: Option[BigDecimal], // DEFAULT 0.00
    spl_3months_monthly_price: Option[BigDecimal],
    spl_6months_monthly_price: Option[BigDecimal],
    spl_1year_monthly_price: Option[BigDecimal],
    spl_additional_user_price: Option[BigDecimal], // DEFAULT 0.00

    spl_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP
    spl_updated_at: Option[LocalDateTime],
    spl_deleted_at: Option[LocalDateTime]
)

object SubscriptionPlanDTO {
    implicit val format: OFormat[SubscriptionPlanDTO] = Json.format[SubscriptionPlanDTO]

    /**
     * create subscription_plan DTO entity
     * @param subscription_plan
     * @return subscription_plan DTO entity
     */
    def create(subscription_plan: SubscriptionPlan): SubscriptionPlanDTO = {
        SubscriptionPlanDTO(
            spl_id = subscription_plan.spl_id,
            spl_can_add_users = subscription_plan.spl_can_add_users,
            spl_included_users_count = subscription_plan.spl_included_users_count,
            spl_max_projects_count = subscription_plan.spl_max_projects_count,
            spl_max_workspaces_count = subscription_plan.spl_max_workspaces_count,
            spl_has_ads = subscription_plan.spl_has_ads,
            spl_can_add_opened_workspace = subscription_plan.spl_can_add_opened_workspace,
            spl_regular_price = subscription_plan.spl_regular_price,
            spl_discount_3months = subscription_plan.spl_discount_3months,
            spl_discount_6months = subscription_plan.spl_discount_6months,
            spl_discount_1year = subscription_plan.spl_discount_1year,
            spl_3months_monthly_price = subscription_plan.spl_3months_monthly_price,
            spl_6months_monthly_price = subscription_plan.spl_6months_monthly_price,
            spl_1year_monthly_price = subscription_plan.spl_1year_monthly_price,
            spl_additional_user_price = subscription_plan.spl_additional_user_price,
            spl_created_at = subscription_plan.spl_created_at,
            spl_updated_at = subscription_plan.spl_updated_at,
            spl_deleted_at = subscription_plan.spl_deleted_at
        )
    }
}