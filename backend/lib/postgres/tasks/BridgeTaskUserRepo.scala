package demo1.taskmanagement.lib.postgres.tasks

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

class BridgeTaskUserRepository(db: Database) {
    private val tasks_users = TableQuery[BridgeTaskUserTable]

    def findById(id: Long): Future[Option[BridgeTaskUser]] = 
        db.run(tasks_users.filter(_.tsk_usr_id === id).result.headOption)

    def findAll(): Future[Seq[BridgeTaskUser]] = 
        db.run(tasks_users.result)
    
}
