package com.taskmanagement.lib.postgres.organizations.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class OrganizationRepository(db: Database) {
    private val organizations = TableQuery[OrganizationTable]

    def findById(id: Long): Future[Option[Organization]] = 
        db.run(organizations.filter(_.org_id === id).result.headOption)

    def findAll(): Future[Seq[Organization]] = 
        db.run(organizations.result)
    
}
