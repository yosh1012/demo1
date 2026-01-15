package com.taskmanagement.lib.redis.task_draft.demo1

// dependencies
import scala.concurrent.{ExecutionContext, Future}
import com.typesafe.scalalogging.LazyLogging
import play.api.libs.json.Json

class TaskDraftService(repo: TaskDraftRedisRepo)
    (implicit executionContext: ExecutionContext) extends LazyLogging {
    
    /**
     * update task explanation draft 
     * @param request
     * @return nothing
     */
    def updateDraft(request: TaskDraftRequest): Future[Unit] = {
        // logger.debug(s"Updating draft for task ${request.tsk_id} from user ${request.usr_id}")
        repo.pushUpdate(request.tsk_id, request.content_update).flatMap { _ =>
            val broadcastMsg: String = Json.obj(
                "type" -> "UPDATE",
                "tsk_id" -> request.tsk_id,
                "usr_id" -> request.usr_id,
                "content_update" -> request.content_update
            ).toString()

            // logger.debug(s"broadcasting update for task ${request.tsk_id}")
            repo.publishUpdate(request.tsk_id, broadcastMsg).map(_ => ())
        }
    }

    /**
     * fetch current task explanation draft
     * @param tsk_id
     * @return async obeject for TaskDraftResponse
     */
    def fetchDraft(tsk_id: Long): Future[TaskDraftResponse] = {
        // logger.debug(s"fetching draft history for task ${tsk_id}") 
        repo.getAllUpdates(tsk_id).map {
            updates: Seq[String] => TaskDraftResponse(tsk_id, updates) // lambda
        }    
    }
    
    /**
     * delete task explanation draft
     * @param tsk_id
     * @return the result of deletion
     */
    def discardDraft(tsk_id: Long): Future[Boolean] = {
        // logger.debug(s"deleting draft history for task ${request.tsk_id}")
        repo.deleteDraft(tsk_id)  
    }
}