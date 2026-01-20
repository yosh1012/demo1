package demo1.taskmanagement.lib.postgres.organizations

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class BridgeOrganizationUserRepository(db: Database) {
    private val organizations_users = TableQuery[BridgeOrganizationUserTable]

    def findById(id: Long): Future[Option[BridgeOrganizationUser]] = 
        db.run(organizations_users.filter(_.org_usr_id === id).result.headOption)

    def findAll(): Future[Seq[BridgeOrganizationUser]] = 
        db.run(organizations_users.result)
    
}
