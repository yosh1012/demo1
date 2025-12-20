# API design

Table List

API domain list
= Bazel build unit(package)

template:

### x. /api/domainname {tablename(RW)} : description

Bazel package: //backend/xxxxx:xxxxx

|endpoint|method|description|login required|role|subscription|
|---|---|---|---|---|---|
|/api/domainname/endpoint|GET|read(list) xxxxx|YES/NO|any or admin|any or required|
|/api/domainname/endpoint|GET|read(detail) xxxxx|YES/NO|any or admin|any or required|
|/api/domainname/endpoint|POST|create xxxxx|YES/NO|any or admin|any or required|
|/api/domainname/endpoint|PUT|update xxxxx|YES/NO|any or admin|any or required|
|/api/domainname/endpoint|DELETE|delete xxxxx|YES/NO|any or admin|any or required|

## 1. /api/auth {users(R), user_sessions(RW)} : Authentication and Session management

Bazel package: //backend/auth:auth

|endpoint|method|description|login required|role|subscription|
|---|---|---|---|---|---|
|/api/auth/login|POST|user login|NO|any|any|
|/api/auth/logout|POST|user logout|YES|any|any|
|/api/auth/refresh|POST|refresh token|YES|any|any|
|/api/auth/verify|GET|verify session|YES|any|any|

## 2. /api/users {users(RW)} : Access to users resources

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


