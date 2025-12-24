package com.taskmanagement.organizations.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class BridgeOrganizationUserTable(tag: Tag) extends Table[BridgeOrganizationUser](tag, "organizations_users") {
    def column_name = column[Type]("column_name", O.PrimaryKey, O.AutoInc)

    def org_usr_created_at = column[java.time.LocalDateTime]("org_usr_created_at") // DEFAULT CURRENT_TIMESTAMP
    def org_usr_updated_at = column[Option[java.time.LocalDateTime]]("org_usr_updated_at")
    def org_usr_deleted_at = column[Option[java.time.LocalDateTime]]("org_usr_deleted_at")

    def * = (
        column_name1, column_name2, column_name3, column_name4,
        column_name5, column_name6, column_name7
    ).mapTo[BridgeOrganizationUserTable]
}

class BridgeOrganizationUserRepository(db: Database) {
    private val organizations_users = TableQuery[BridgeOrganizationUserTable]

    def findById(id: Long): Future[Option[BridgeOrganizationUser]] = 
        db.run(organizations_users.filter(_.org_usr_id === id).result.headOption)

    def findAll(): Future[Seq[BridgeOrganizationUser]] = 
        db.run(organizations_users.result)
    
}
