# backend

## abstract

This project has the "data-driven" naming rule.

- create application.conf to connect to DB and Redis
- create Main.scala and main/BUILD.bazel

## coding rules

As much as possible, dependencies and types should be explicitly written
BUT never use alias for Type definition for consistancy and easy-to-read
and, files which generally formatted like model files, can omit them.

Model: can omit explicit dependency
Repo:  can omit explicit dependency
Routes: can omit explicit dependency
Middleware(Handlers): never omit
Service: never omit
Main: never omit

### examples

```
import org.apache.pekko.http.scaladsl.server.{Directives => D}._
import org.apache.pekko.http.scaladsl.server.{Directive0 => D0, Route => R}

    private def corsResponseHeaders(originUrl: String): Directive0 = { // never abbreviate for definition 
        D.respondWithHeaders( // use prefix 
            `Access-Control-Allow-Origin`(HttpOrigin(originUrl)),
            `Access-Control-Allow-Methods`(allowedMethods),
            `Access-Control-Allow-Headers`(allowedHeaders),
            `Access-Control-Allow-Credentials`(true)
        )
    }
```

## structure

backend/
-- api/v1/
    -- admin
        -- audit
        -- organization
        -- projects
        -- workspaces
    -- auth
    -- me
        -- notifications
        -- profile
        -- projects
        -- related_tasks
        -- sprints
        -- tasks
        -- work_times
-- lib
    -- http (http common libs)
    -- activity_logs
        -- ActivityLog.scala (data model)
        -- ActivityLogTable.scala (table model)
        -- ActivityLogRepo.scala (repo file)
    -- notifications
    -- organizations
    -- projects
    -- sprints
    -- subscription_plans
    -- tasks
    -- user_sessions
    -- users
    -- work_times
    -- workspaces
-- main

## Lib 

### JSON Library: Play JSON

#### Reason for adoption

- Spray JSON doesn't support more than 22 columns