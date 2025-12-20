# subscription plans

possibly added another plan in the future, so designed the subscription_plans table not as ENUM.

## Free

- user can create maximam 2 projects
- automatically generate org(generated name) and workspace(user's name), and user never see their org's name
- show ADs
- for free

## Personal

- user has no limitation with creating projects
- automatically generate org(generated name) and workspace(user's name), and user never see their org's name
- never show ADs
- 3CAD / 3USD / 380JPY per month

## Team

- 5 free users included, except for the contractor
- contractor will name the organization name first, then it will be a display name. *when the contractor upgrades their plan from Free or Personal, show a modal to name it.
- The contactor will stick to the admin role
- When the contractor wants to change the contractor person, needs to ask the support of this service. By executing a script, will deal with this ask.
- The contractor can set the admin role not only them, but also other users in this org.
- Workspace feature is available on this plan. Maximum 5 workspaces.
- The admin role person can create a new workspace, then can set a workspace manager role person for each workspace.
- but this plan is limited only with the closed workspace, which other org users cannot join.
- When the team contractor downgraded the plan from Team to Free or Personal, all workspaces included users except for the contractor will be archived. The warning must be shown loudly. Users except for the contractor will be disabled.
- 18CAD / 12USD / 1,980JPY per month
- If the contractor wants to add more than 5 users, will cost 2.5CAD / 2USD / 250JPY per person.

## Enterprise

- 15 free users included, except for the contractor
- contractor will name the organization name first, then it will be a display name. *when the contractor upgrades their plan from Free or Personal, show a modal to name it.
- The contactor will stick to the admin role
- When the contractor wants to change the contractor person, needs to ask the support of this service. By executing a script, will deal with this ask.
- The contractor can set the admin role not only them, but also other users in this org.
- Workspace feature is available on this plan. No limitation
- The admin role person can create a new workspace, then can set a workspace manager role person for each workspace.
- The opened workspace is available on this plan, and other org's users can join this workspace.
- When the enterprise contractor downgraded the plan from Enterprise to Team, the opened workspaces created by this org, will remain. However, the workspace flag "is_opened" will turn into false. Then, other org's users cannot see this workspace anymore.
- When the enterprise contractor downgraded the plan from Enterprise to Free or Personal, all workspaces included users except for the contractor will be archived. The warning must be shown loudly. Users except for the contractor will be disabled.
- 48CAD / 33USD / 5,000JPY per month
- If the contractor wants to add more than 15 users, will cost 2.5CAD / 2USD / 250JPY per person.