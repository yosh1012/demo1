# API design

Table List


API domain list




## 1. /api/auth (users, user_sessions): 

Access to: functions to authenticate
Bazel package: //backend/auth:auth

- register a new user
- login
- logout
- verify session

|endpoint|method|description|login required|subscription|
|---|---|---|---|---|
|/api/auth/register|POST|register a new user|NO|free|
|/api/auth/login|POST|user login|NO|free|
|/api/auth/logout|POST|user logout|YES|free|
|/api/auth/logout|POST|user logout|YES|free|

## 2. /api/users (users): 

Access to: User resources
Bazel package: //backend/users:users

- show user's profile
- update user's profile
- change password

### subscription (subscription_plans): /api/subscriptions

Access to: subscription_plans resources
Bazel package: //backend/subscriptions:subscriptions
