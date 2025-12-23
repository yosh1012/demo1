# DB DESIGN SUMMARY

## tables list

- users

- user_sessions

- subscription_plans

- organizations
- organizations_users (bridge)

- workspaces
- workspaces_users (bridge)

- projects
- projects_users (bridge)

- sprints

- tasks
- tasks_users (bridge)
- task_categories
- tasks_task_categories (bridge)
- comments
- attachments
- notifications

- activity_logs

- work_times

## ENUM types
- task_status ('todo', 'in_progress', 'in_review', 'pending', 'done')
- notification_level ('appinfo', 'mention', 'assign', 'remind', 'alert')
- subscription_status ('active', 'canceled', 'expired', 'trial', 'unpaid')
