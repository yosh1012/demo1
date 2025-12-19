# DB DESIGN

## ENUM TYPES

- task_status ('todo', 'in_progress', 'in_review', 'pending', 'done')
- notification_level ('appinfo', 'mention', 'assign', 'remind', 'alert')
- subscription_status ('active', 'canceled', 'expired', 'trial', 'unpaid')

## USER AUTHENTICATION AND SESSIONS

user account information and session store

- users
- user_sessions (physical delete)

## SUBSCRIPTION

- subscription_plans(static table)

## ORGANIZATION

organization information, which users belong to
the top level group

- organizations
- organizations_users (bridge table between organization and users)

## WORKSPACE

workspace information, which users belong to
the second level group

- workspaces
- workspaces_users (bridge table between workspaces and users)

## PROJECT

- projects
- projects_users (bridge table between projects and users)

## TASK

- tasks (supports realtime editing with optimistic locking)
- tasks_users (bridge table)
- sprints
- task_categories
- tasks_task_categories (bridge table)
- comments (direct link to task)
- attachments (includes file metadata)

## NOFITICATION

- notifications
- activity_logs

## TIME TRACKING

- work_times

