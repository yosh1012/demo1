package com.taskmanagement.subscription_plans.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class SubscriptionPlanTable(tag: Tag) extends Table[SubscriptionPlan](tag, "subscription_plans") {
    def column_name = column[Type]("column_name", O.PrimaryKey, O.AutoInc)

    def spl_created_at = column[java.time.LocalDateTime]("spl_created_at") // DEFAULT CURRENT_TIMESTAMP
    def spl_updated_at = column[Option[java.time.LocalDateTime]]("spl_updated_at")
    def spl_deleted_at = column[Option[java.time.LocalDateTime]]("spl_deleted_at")

    def * = (
        column_name1, column_name2, column_name3, column_name4,
        column_name5, column_name6, column_name7
    ).mapTo[SubscriptionPlan]
}

class SubscriptionPlanRepository(db: Database) {
    private val subscription_plans = TableQuery[SubscriptionPlanTable]

    def findById(id: Long): Future[Option[Workspace]] = 
        db.run(subscription_plans.filter(_.spl_id === id).result.headOption)

    def findAll(): Future[Seq[Workspace]] = 
        db.run(subscription_plans.result)
    
}
