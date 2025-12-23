package com.taskmanagement.organizations.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class OrganizationTable(tag: Tag) extends Table[Organization](tag, "organizations") {
    def column_name = column[Type]("column_name", O.PrimaryKey, O.AutoInc)

    def org_created_at = column[java.time.LocalDateTime]("org_created_at") // DEFAULT CURRENT_TIMESTAMP
    def org_updated_at = column[Option[java.time.LocalDateTime]]("org_updated_at")
    def org_deleted_at = column[Option[java.time.LocalDateTime]]("org_deleted_at")

    def * = (
        column_name1, column_name2, column_name3, column_name4,
        column_name5, column_name6, column_name7
    ).mapTo[Organization]
}

class OrganizationRepository(db: Database) {
    private val organizations = TableQuery[OrganizationTable]

    def findById(id: Long): Future[Option[Organization]] = 
        db.run(organizations.filter(_.org_id === id).result.headOption)

    def findAll(): Future[Seq[Organization]] = 
        db.run(organizations.result)
    
}
