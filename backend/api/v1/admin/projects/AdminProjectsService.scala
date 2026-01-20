package demo1.taskmanagement.api.v1.admin.projects

import scala.concurrent.{Future, ExecutionContext}

class AdminProjectsService()(implicit executionContext: ExecutionContext) {
    def endpoint1(arg1: Type, arg2: Type): Future[Type] = {
        Future.successful("endpoint1 success")
    }

    def endpoint2(arg1: Type, arg2: Type): Future[Type] = {
        Future.successful("endpoint2 success")
    }
}
