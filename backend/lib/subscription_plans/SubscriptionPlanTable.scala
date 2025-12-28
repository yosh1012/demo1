package com.taskmanagement.subscription_plans.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class SubscriptionPlanTable(tag: Tag) extends Table[SubscriptionPlan](tag, "subscription_plans") {
    def spl_id = column[Int]("spl_id", O.PrimaryKey, O.AutoInc)
    def spl_can_add_users = column[Boolean]("spl_can_add_users")
    def spl_included_users_count = column[Int]("spl_included_users_count")
    def spl_max_projects_count = column[Int]("spl_max_projects_count")
    def spl_max_workspaces_count = column[Int]("spl_max_workspaces_count")
    def spl_has_ads = column[Boolean]("spl_has_ads")
    def spl_can_add_opened_workspace = column[Boolean]("spl_can_add_opened_workspace")

    def spl_regular_price = column[Decimal]("spl_regular_price")
    def spl_discount_3months = column[Option[Decimal]]("spl_discount_3months")
    def spl_discount_6months = column[Option[Decimal]]("spl_discount_6months")
    def spl_discount_1year = column[Option[Decimal]]("spl_discount_1year")
    def spl_3months_monthly_price = column[Option[Decimal]]("spl_3months_monthly_price")
    def spl_6months_monthly_price = column[Option[Decimal]]("spl_6months_monthly_price")
    def spl_1year_monthly_price = column[Option[Decimal]]("spl_1year_monthly_price")
    def spl_additional_user_price = column[Option[Decimal] ]("spl_additional_user_price")

    def spl_created_at = column[LocalDateTime]("spl_created_at")
    def spl_updated_at = column[Option[LocalDateTime]]("spl_updated_at")
    def spl_deleted_at = column[Option[LocalDateTime]]("spl_deleted_at")

    def * = (
		spl_id,
		spl_can_add_users,
		spl_included_users_count,
		spl_max_projects_count,
		spl_max_workspaces_count,
		spl_has_ads,
		spl_can_add_opened_workspace,

		spl_regular_price,
		spl_discount_3months,
		spl_discount_6months,
		spl_discount_1year,
		spl_3months_monthly_price,
		spl_6months_monthly_price,
		spl_1year_monthly_price,
		spl_additional_user_price,

		spl_created_at,
		spl_updated_at,
		spl_deleted_at
    ).mapTo[SubscriptionPlan]
}
