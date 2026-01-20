package demo1.taskmanagement.lib.postgres.tasks

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.{Future, ExecutionContext}
import java.time.LocalDateTime

class TaskRepository(db: Database)(implicit ec: ExecutionContext) {
    private val tasks = TableQuery[TaskTable] // slick

    // principle: one repo function can query just once

    /**
     * find task entity by id
     * @param id
     * @return Task entity, or Future.failed()
     */
    def findById(id: Long): Future[Option[Task]] = 
        db.run(tasks.filter(_.tsk_id === id).result.headOption)

    /**
     * get task entity list
     * @return Task entity list, or Future.failed()
     */
    def findAll(): Future[Seq[Task]] = 
        db.run(tasks.result)

    /**
     * get task entity list by project id
     * @param prj_id
     * @return Task entity list, or Future.failed()
     */
    def findByProjectId(prj_id: Long): Future[Seq[Task]] = {
        db.run( // slick dsl
            tasks
                .filter(_.prj_id === prj_id)
                .filter(_.tsk_deleted_at.isEmpty)
                .result
        )
    }

    /**
     * create a new task
     * @param taskModel
     * @return Updated task entity including PK, or Future.failed()
     */
    def create(taskModel: Task): Future[Task] = {
        val insertQuery = tasks
            .returning(tasks.map(_.tsk_id))
            .into { (task: Task, generatedId: Long) =>
                task.copy(tsk_id = generatedId)
            }
        db.run(insertQuery += taskModel)
    }

    /**
     * update task
     * @param paramName
     * @return the number of updated records, normally 0 or 1, or Future.failed()
     */
    def update(taskModel: Task): Future[Int] = 
        db.run(
            tasks
                .filter(_.tsk_id === taskModel.tsk_id)
                .map{(t: TaskTable) => // need to define t: TaskTable because _ will be considered each different arg normaly, this is slick dsl
                    (t.tsk_name, t.tsk_description, t.tsk_start_date, t.tsk_due_date, t.tsk_status, t.tsk_updated_at)
                }
                .update((
                    taskModel.tsk_name,
                    taskModel.tsk_description,
                    taskModel.tsk_start_date,
                    taskModel.tsk_due_date,
                    taskModel.tsk_status,
                    Some(LocalDateTime.now())
                ))
        )

    /**
     * softDelete task
     * @param tsk_id
     * @return the number of softDeleted task, normally 0 or 1, or Future.failed()
     */
    def softDelete(tsk_id: Long): Future[Int] =
        db.run(
            tasks
                .filter(_.tsk_id === tsk_id)
                .map(_.tsk_deleted_at)
                .update(Some(LocalDateTime.now()))
        )
    

}
