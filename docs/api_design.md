# API design

## overview

API prefix: /api/v1
API domain group: /me, /admin 

API domain endpoint name
= Bazel build unit(package name)

### API domain

/auth

/me/profile
/me/projects
/me/projects/${hashed_prj_id}/sprints
/me/projects/${hashed_prj_id}/tasks
/me/notifications
/me/related-tasks
/me/work-time

/admin/organization
/admin/workspaces
/admin/projects
/admin/audit

must implement "Hashids" to hide table's PK
- Hashids.scala?

template:
```
## x. /api/v1/domainname : description

Bazel package: //backend/xxxxx:xxxxx
Tables: table(RW)

|endpoint|method|description|login required|role|subscription|
|---|---|---|---|---|---|
|/api/domainname/endpoint|GET|description|YES/NO|any or admin|any or required|
|/api/domainname/endpoint|GET|description|YES/NO|any or admin|any or required|

```

## 1. /api/v1/auth : Authentication and Session management

Bazel package: //backend/auth:auth
Tables: users(R), user_sessions(RW)

|endpoint|method|description|login required|role|subscription|
|---|---|---|---|---|---|
|/auth/signup|POST|register a new user|NO|any|any|
|/auth/login|POST|login user|NO|any|any|
|/auth/verify|POST|verify user login session|YES|any|any|
|/auth/refresh|POST|refresh user access token|YES|any|any|
|/auth/logout|POST|logout user|YES|any|any|
|/auth/password|POST|reset password|NO|any|any|

## 2. /api/v1/me : Access to user resources

Bazel package: //backend/users:users

|endpoint|method|description|login required|role|subscription|
|---|---|---|---|---|---|
|/api/users/register|POST|register a new user|NO|any|any|
|/api/users/me|GET|show user's profile|YES|any|any|
|/api/users/me|PUT|update user's profile|YES|any|any|
|/api/users/me/password|update user's password|YES|any|any|

### 3. /api/organizations {subcription_plans(RW), organizations(RW), organizations_users(RW), users(RW): Access to organization and its dependent resouceses

Bazel package: //backend/organizations:organizations

|endpoint|method|description|login required|role|subscription|
|---|---|---|---|---|---|
|/api/organizations/me|GET|show my organization profile|YES|admin|Team or Enterprise|
|/api/organizations/endpoint|PUT|update my organization profile|YES|admin|Team or Enterprise|
|/api/organizations/me/delete|DELETE|delete my organization|YES|admin|Team or Enterprise|
|/api/organizations/me/delete_confirmation|GET|show my organization delete confirmation page|YES|admin|Team or Enterprise|
|/api/organizations/me/users|GET|show my organization users(list)|YES|admin|Team or Enterprise|
|/api/organizations/me/users/:${userId}|GET|show my organization user detail|YES|admin|Team or Enterprise|
|/api/organizations/me/users/:${userId}|GET|show my organization user detail|YES|admin|Team or Enterprise|
|/api/organizations/me/users/:${userId}|GET|show my organization user detail|YES|admin|Team or Enterprise|
|/api/organizations/me/users/:${userId}|DELETE|delete my organization user|YES|admin|Team or Enterprise|
|/api/organizations/me/users/:${userId}/delete_confirmation|GET|show my organization user delete confirmation page|YES|admin|Team or Enterprise|
|/api/organizations/me/subscription|PUT|update organization's subscription plan|YES|admin|Team or Enterprise|

- organizations
- organizations_users
- subscription_plans

### workspaces

- workspaces
- workspaces_users

### projects

- projects
- projects_users
- sprints

### tasks

- tasks
- tasks_users
- task_categories
- tasks_task_categories
- comments
- attachments

### notifications

- notifications

### activity_logs

- activity_logs

### work_times

- work_times


