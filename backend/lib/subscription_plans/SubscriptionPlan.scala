package com.taskmanagement.subscription_plans.demo1

import dependencies

case class SubscriptionPlan(
    spl_id: Int, // PK
    spl_can_add_users: Boolean // DEFALUT false
    spl_included_users_count: Int // DEFAULT 1
    spl_max_projects_count: Int // DEFAULT 1
    spl_max_workspaces_count: Int // DEFAULT 1
    spl_has_ads: Boolean // DEFAULT false


    tbl_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP
    tbl_updated_at: Option[LocalDateTime],
    tbl_deleted_at: Option[LocalDateTime]
)
