package demo1.taskmanagement.api.v1.me.tasks

import scala.concurrent.{Future, ExecutionContext}

import demo1.taskmanagement.lib.tasks.{TaskCreateRequest, TaskUpdateRequest, TaskResponse}
import demo1.taskmanagement.lib.postgres.tasks.{Task, TaskRepository}

class TasksService(taskRepo: TaskRepository)(implicit executionContext: ExecutionContext) {

    /**
     * get all tasks
     * @param prj_id
     * @return all tasks list
     */
    def getAllTasks(prj_id: Long): Future[Seq[Task]] = {
        taskRepo.findByProjectId(prj_id)
    }

    /**
     * get task detail
     * @param tsk_id
     * @return task model, or 
     */
    def getTaskDetail(tsk_id: Long): Future[Option[Task]] = {
        taskRepo.findById(tsk_id)
    }

    /**
     * create task
     * @param taskModel
     * @return the number of updated records, or Future.failed()
     */
    def createTask(prj_id: Long, taskCreateRequestData: TaskCreateRequest): Future[Task] = {

        val taskModel: Task = TaskCreateRequest.createModel(taskCreateRequestData, prj_id)
        taskRepo.create(taskModel)
    }

    /**
     * update task
     * @param taskModel
     * @return the number of updated records, or Future.failed()
     */
    def updateTask(tsk_id: Long, existingTask: Task, taskUpdateRequestData: TaskUpdateRequest): Future[Int] = {
        val updatedTask: Task = existingTask.copy( // case class .copy
            tsk_name = taskUpdateRequestData.tsk_name.getOrElse(existingTask.tsk_name),
            tsk_status = taskUpdateRequestData.tsk_status.getOrElse(existingTask.tsk_status),
            tsk_description = taskUpdateRequestData.tsk_description.orElse(existingTask.tsk_description),
            tsk_start_date = taskUpdateRequestData.tsk_start_date.orElse(existingTask.tsk_start_date),
            tsk_due_date = taskUpdateRequestData.tsk_due_date.orElse(existingTask.tsk_due_date),
            tsk_estimated_hours = taskUpdateRequestData.tsk_estimated_hours.orElse(existingTask.tsk_estimated_hours),
        )
        taskRepo.update(updatedTask)
    }

    /**
     * logical delete task
     * @param tsk_id
     * @return 
     */
    def deleteTask(tsk_id: Long): Future[Int] = {
        taskRepo.softDelete(tsk_id)
    }

}
