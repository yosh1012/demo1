# backend

This project has the "data-driven" naming rule.

- create application.conf to connect to DB and Redis
- create Main.scala and main/BUILD.bazel

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

# Lib 

## JSON Library: Play JSON

### Reason for adoption

- Spray JSON doesn't support more than 22 columns