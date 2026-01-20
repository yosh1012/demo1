package demo1.taskmanagement.api.v1.me.tasks

import org.apache.pekko.http.scaladsl.server.{Directives => D}
import org.apache.pekko.http.scaladsl.server.Route
import org.apache.pekko.http.scaladsl.model.StatusCodes

import scala.concurrent.ExecutionContext
import scala.util.{Success, Failure}

import demo1.taskmanagement.lib.auth.AuthHandler
import demo1.taskmanagement.lib.hashids.HashidsHandler
import demo1.taskmanagement.lib.http.JsonSupport
import demo1.taskmanagement.lib.tasks.{TaskCreateRequest, TaskResponse, TaskUpdateRequest}
import demo1.taskmanagement.lib.postgres.tasks.Task

class TasksRoutes(tasksService: TasksService)(implicit ec: ExecutionContext) extends JsonSupport {
    val routes: Route = D.pathPrefix("me") {
        D.pathPrefix("projects") {
            D.pathPrefix(D.Segment) { (hashed_prj_id: String) =>
                D.pathPrefix("tasks") {

                    // decode hashed project id
                    HashidsHandler.decode(hashed_prj_id) match {
                        case None =>
                            D.complete(StatusCodes.BadRequest, "Invalid project id")

                        case Some(prj_id: Long) =>
                            // JWT authentication
                            AuthHandler.authenticate { (usr_id: Long) =>
                                D.concat(
                                    // /me/projects/{prj_id}/tasks
                                    D.pathEnd {
                                        D.concat(
                                            // get all tasks
                                            D.get {
                                                D.onComplete(tasksService.getAllTasks(prj_id)) {
                                                    case Success(tasks: Seq[Task]) =>
                                                        val taskResponses: Seq[TaskResponse] = tasks.map { (taskModel: Task) =>
                                                            TaskResponse.createTaskResponse(taskModel)
                                                        }
                                                        D.complete(StatusCodes.OK, taskResponses)

                                                    case Failure(ex: Throwable) =>
                                                        D.complete(StatusCodes.InternalServerError, s"Error: ${ex.getMessage}")
                                                }
                                            },
                                            // create a new task
                                            D.post {
                                                D.entity(D.as[TaskCreateRequest]) { (taskCreateRequestData: TaskCreateRequest) =>
                                                    D.onComplete(tasksService.createTask(prj_id, taskCreateRequestData)) {
                                                        case Success(createdTask: Task) =>
                                                            val taskResponseData: TaskResponse = TaskResponse.createTaskResponse(createdTask)
                                                            D.complete(StatusCodes.Created, taskResponseData)

                                                        case Failure(ex: Throwable) =>
                                                            D.complete(StatusCodes.InternalServerError, s"Error: ${ex.getMessage}")
                                                    }
                                                }
                                            }
                                        )
                                    },

                                    // /me/projects/{prj_id}/tasks/{tsk_id}
                                    D.path(D.Segment) { (hashed_tsk_id: String) =>
                                        // decode hashed task id
                                        HashidsHandler.decode(hashed_tsk_id) match {
                                            case None =>
                                                D.complete(StatusCodes.BadRequest, "Invalid task ID")

                                            case Some(tsk_id: Long) =>
                                                D.concat(
                                                    // get task detail for tsk_id
                                                    D.get {
                                                        D.onComplete(tasksService.getTaskDetail(tsk_id)) {
                                                            case Success(Some(taskModel: Task)) =>
                                                                val taskResponseData: TaskResponse = TaskResponse.createTaskResponse(taskModel)
                                                                D.complete(StatusCodes.OK, taskResponseData)

                                                            case Success(None) =>
                                                                D.complete(StatusCodes.NotFound, "Task not found")

                                                            case Failure(ex: Throwable) =>
                                                                D.complete(StatusCodes.InternalServerError, s"Error: ${ex.getMessage}")
                                                        }
                                                    },
                                                    // update task for tsk_id
                                                    D.put {
                                                        D.entity(D.as[TaskUpdateRequest]) { (taskUpdateRequestData: TaskUpdateRequest) =>
                                                            // get existing task first, then update
                                                            D.onComplete(tasksService.getTaskDetail(tsk_id)) {
                                                                case Success(Some(existingTask: Task)) =>
                                                                    D.onComplete(tasksService.updateTask(tsk_id, existingTask, taskUpdateRequestData)) {
                                                                        case Success(rowCount: Int) if rowCount > 0 =>
                                                                            // re-fetch updated task to return
                                                                            D.onComplete(tasksService.getTaskDetail(tsk_id)) {
                                                                                case Success(Some(updatedTask: Task)) =>
                                                                                    val taskResponseData: TaskResponse = TaskResponse.createTaskResponse(updatedTask)
                                                                                    D.complete(StatusCodes.OK, taskResponseData)

                                                                                case _ =>
                                                                                    D.complete(StatusCodes.InternalServerError, "Failed to retrieve updated task")
                                                                            }

                                                                        case Success(_) =>
                                                                            D.complete(StatusCodes.NotFound, "Task not found")

                                                                        case Failure(ex: Throwable) =>
                                                                            D.complete(StatusCodes.InternalServerError, s"Error: ${ex.getMessage}")
                                                                    }

                                                                case Success(None) =>
                                                                    D.complete(StatusCodes.NotFound, "Task not found")

                                                                case Failure(ex: Throwable) =>
                                                                    D.complete(StatusCodes.InternalServerError, s"Error: ${ex.getMessage}")
                                                            }
                                                        }
                                                    },
                                                    // delete task for tsk_id
                                                    D.delete {
                                                        D.onComplete(tasksService.deleteTask(tsk_id)) {
                                                            case Success(rowCount: Int) if rowCount > 0 =>
                                                                D.complete(StatusCodes.NoContent)

                                                            case Success(_) =>
                                                                D.complete(StatusCodes.NotFound, "Task not found")

                                                            case Failure(ex: Throwable) =>
                                                                D.complete(StatusCodes.InternalServerError, s"Error: ${ex.getMessage}")
                                                        }
                                                    }
                                                )
                                        }
                                    }
                                )
                            }
                    }
                }
            }
        }
    }
}