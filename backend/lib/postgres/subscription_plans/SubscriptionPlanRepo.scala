package demo1.taskmanagement.lib.postgres.subscription_plans

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class SubscriptionPlanRepository(db: Database) {
    private val subscription_plans = TableQuery[SubscriptionPlanTable]

    def findById(id: Int): Future[Option[SubscriptionPlan]] = 
        db.run(subscription_plans.filter(_.spl_id === id).result.headOption)

    def findAll(): Future[Seq[SubscriptionPlan]] = 
        db.run(subscription_plans.result)
    
}
