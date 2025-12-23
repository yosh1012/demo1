package com.taskmanagement.sprints.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class SprintTable(tag: Tag) extends Table[Sprint](tag, "sprints") {
    def column_name = column[Type]("column_name", O.PrimaryKey, O.AutoInc)

    def spr_created_at = column[java.time.LocalDateTime]("spr_created_at") // DEFAULT CURRENT_TIMESTAMP
    def spr_updated_at = column[Option[java.time.LocalDateTime]]("spr_updated_at")
    def spr_deleted_at = column[Option[java.time.LocalDateTime]]("spr_deleted_at")

    def * = (
        column_name1, column_name2, column_name3, column_name4,
        column_name5, column_name6, column_name7
    ).mapTo[Sprint]
}

class SprintRepository(db: Database) {
    private val sprints = TableQuery[SprintTable]

    def findById(id: Long): Future[Option[Sprint]] = 
        db.run(sprints.filter(_.spr_id === id).result.headOption)

    def findAll(): Future[Seq[Sprint]] = 
        db.run(sprints.result)
    
}
