-- ENUM definition
CREATE TYPE SUBSCRIPTION_STATUS AS ENUM('active', 'canceled', 'expired', 'trial', 'unpaid');
CREATE TYPE TASK_STATUS AS ENUM('todo', 'in_progress', 'in_review', 'pending', 'done');
CREATE TYPE NOTIFICATION_LEVEL AS ENUM('appinfo', 'mention', 'assign', 'remind', 'alert');

-- users
CREATE TABLE users(
    usr_id BIGSERIAL PRIMARY KEY,

    usr_email VARCHAR(255) NOT NULL,
    usr_first_name VARCHAR(100),
    usr_last_name VARCHAR(100),
    usr_display_name VARCHAR(55),
    usr_hashed_password VARCHAR(255),
    usr_profile_image_url TEXT,
    usr_timezone VARCHAR(55),
    usr_lang_preference VARCHAR(55),
    usr_last_login_at TIMESTAMP,
    usr_is_active BOOLEAN NOT NULL,

    usr_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usr_updated_at TIMESTAMP,
    usr_deleted_at TIMESTAMP
);

CREATE UNIQUE INDEX unq_users_on_usr_email
ON users (usr_email)
WHERE usr_deleted_at IS NULL;

-- user_sessions
CREATE TABLE user_sessions(
    ssn_id UUID PRIMARY KEY,
    
    ssn_jwt_refresh_hashed_token VARCHAR(255) NOT NULL,
    ssn_usr_agent TEXT,
    ssn_ip_address VARCHAR(45),
    ssn_will_expire_at TIMESTAMP NOT NULL,
    ssn_last_accessed_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    ssn_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ssn_updated_at TIMESTAMP,

    usr_id BIGINT NOT NULL REFERENCES users(usr_id) ON DELETE CASCADE
);

CREATE INDEX idx_user_sessions_fk_usr_id
ON user_sessions(usr_id);

-- subscription_plans
CREATE TABLE subscription_plans(
    spl_id INT PRIMARY KEY,

    spl_name VARCHAR(55) NOT NULL,
    spl_can_add_users BOOLEAN NOT NULL DEFAULT false,
    spl_included_users_count INT NOT NULL DEFAULT 1,
    spl_max_projects_count INT NOT NULL DEFAULT 1,
    spl_max_workspaces_count INT NOT NULL DEFAULT 1,
    spl_has_ads BOOLEAN NOT NULL DEFAULT false,
    spl_can_add_opened_workspace BOOLEAN NOT NULL DEFAULT false,

    spl_regular_price DECIMAL NOT NULL,
    spl_discount_3months DECIMAL DEFAULT 0.00,
    spl_discount_6months DECIMAL DEFAULT 0.00,
    spl_discount_1year DECIMAL DEFAULT 0.00,
    spl_3months_monthly_price DECIMAL GENERATED ALWAYS AS (spl_regular_price * (1 - spl_discount_3months)) STORED,
    spl_6months_monthly_price DECIMAL GENERATED ALWAYS AS (spl_regular_price * (1 - spl_discount_6months)) STORED,
    spl_1year_monthly_price DECIMAL GENERATED ALWAYS AS (spl_regular_price * (1 - spl_discount_1year)) STORED,
    spl_additional_user_price DECIMAL DEFAULT 0.00,

    spl_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    spl_updated_at TIMESTAMP,
    spl_deleted_at TIMESTAMP
);

-- organizations
CREATE TABLE organizations(
    org_id BIGSERIAL PRIMARY KEY,

    org_name VARCHAR(255) NOT NULL,
    org_description TEXT,
    org_is_active BOOLEAN NOT NULL,
    org_current_user_count INT NOT NULL DEFAULT 1,
    org_subscription_started_date DATE,
    org_subscription_end_date DATE,
    org_subscription_status SUBSCRIPTION_STATUS NOT NULL,
    org_subscription_is_autorenew BOOLEAN NOT NULL DEFAULT true,
    org_subscription_canceled_at TIMESTAMP,

    org_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    org_updated_at TIMESTAMP,
    org_deleted_at TIMESTAMP,

    spl_id INT NOT NULL REFERENCES subscription_plans(spl_id) ON DELETE CASCADE,
    usr_id BIGINT NOT NULL REFERENCES users(usr_id) ON DELETE CASCADE
);

CREATE INDEX idx_organizations_fk_usr_id
ON organizations(usr_id);

-- organizations_users (bridge)
CREATE TABLE organizations_users(
    org_usr_id BIGSERIAL PRIMARY KEY,

    org_usr_is_admin BOOLEAN NOT NULL DEFAULT false,

    org_usr_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    org_usr_updated_at TIMESTAMP,
    org_usr_deleted_at TIMESTAMP,

    org_id BIGINT NOT NULL REFERENCES organizations(org_id) ON DELETE CASCADE,
    usr_id BIGINT NOT NULL REFERENCES users(usr_id) ON DELETE CASCADE
);

CREATE INDEX idx_organization_users_fk_org_id
ON organizations_users(org_id);

CREATE UNIQUE INDEX unq_organization_users_on_usr_id
ON organizations_users(usr_id)
WHERE org_usr_deleted_at IS NULL;

-- workspaces
CREATE TABLE workspaces(
    wks_id BIGSERIAL PRIMARY KEY,

    wks_name VARCHAR(255) NOT NULL,
    wks_description TEXT,
    wks_is_archived BOOLEAN NOT NULL DEFAULT false,
    wks_archived_at TIMESTAMP,
    wks_is_opened BOOLEAN NOT NULL DEFAULT false,

    wks_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    wks_updated_at TIMESTAMP,
    wks_deleted_at TIMESTAMP,

    org_id BIGINT NOT NULL REFERENCES organizations(org_id) ON DELETE CASCADE,
    usr_id_who_archived BIGINT REFERENCES users(usr_id)
);

CREATE INDEX idx_workspaces_fk_org_id
ON workspaces(org_id);

-- workspaces_users (bridge)
CREATE TABLE workspaces_users(
    wks_usr_id BIGSERIAL PRIMARY KEY,

    wks_usr_is_manager BOOLEAN NOT NULL DEFAULT false,

    wks_usr_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    wks_usr_updated_at TIMESTAMP,
    wks_usr_deleted_at TIMESTAMP,

    wks_id BIGINT NOT NULL REFERENCES workspaces(wks_id) ON DELETE CASCADE,
    usr_id BIGINT NOT NULL REFERENCES users(usr_id) ON DELETE CASCADE
);

CREATE INDEX idx_workspaces_users_fk_wks_id
ON workspaces_users(wks_id);

CREATE INDEX idx_workspaces_users_fk_usr_id
ON workspaces_users(usr_id);

CREATE UNIQUE INDEX unq_workspaces_users_on_wks_id_and_usr_id
ON workspaces_users(wks_id, usr_id)
WHERE wks_usr_deleted_at IS NULL;

-- projects
CREATE TABLE projects(
    prj_id BIGSERIAL PRIMARY KEY,

    prj_name VARCHAR(255) NOT NULL,
    prj_description TEXT,
    prj_is_archived BOOLEAN NOT NULL DEFAULT false,
    prj_archived_at TIMESTAMP,

    prj_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    prj_updated_at TIMESTAMP,
    prj_deleted_at TIMESTAMP,

    wks_id BIGINT NOT NULL REFERENCES workspaces(wks_id) ON DELETE CASCADE,
    usr_id_who_archived BIGINT REFERENCES users(usr_id)
);

CREATE INDEX idx_projects_fk_wks_id
ON projects(wks_id);

-- projects_users (bridge)
CREATE TABLE projects_users(
    prj_usr_id BIGSERIAL PRIMARY KEY,

    prj_usr_is_lead BOOLEAN NOT NULL DEFAULT false,
    prj_usr_is_contributor BOOLEAN NOT NULL DEFAULT false,

    prj_usr_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    prj_usr_updated_at TIMESTAMP,
    prj_usr_deleted_at TIMESTAMP,

    prj_id BIGINT NOT NULL REFERENCES projects(prj_id) ON DELETE CASCADE,
    usr_id BIGINT NOT NULL REFERENCES users(usr_id) ON DELETE CASCADE
);

CREATE INDEX idx_projects_users_fk_prj_id
ON projects_users(prj_id);

CREATE INDEX idx_projects_users_fk_usr_id
ON projects_users(usr_id);

CREATE UNIQUE INDEX unq_projects_users_on_prj_id_and_usr_id
ON projects_users(prj_id, usr_id)
WHERE prj_usr_deleted_at IS NULL;

-- sprints
CREATE TABLE sprints(
    spr_id BIGSERIAL PRIMARY KEY,

    spr_name VARCHAR(255) NOT NULL,
    spr_start_date DATE NOT NULL,
    spr_end_date DATE NOT NULL,

    spr_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    spr_updated_at TIMESTAMP,
    spr_deleted_at TIMESTAMP,

    prj_id BIGINT NOT NULL REFERENCES projects(prj_id) ON DELETE CASCADE
);

CREATE INDEX idx_sprints_fk_prj_id
ON sprints(prj_id);

-- tasks
CREATE TABLE tasks(
    tsk_id BIGSERIAL PRIMARY KEY,

    tsk_name VARCHAR(255) NOT NULL,
    tsk_description TEXT,
    tsk_description_version BIGINT NOT NULL DEFAULT 1,
    tsk_start_date DATE,
    tsk_due_date DATE,
    tsk_estimated_hours DECIMAL,
    tsk_status TASK_STATUS NOT NULL DEFAULT 'todo',

    tsk_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    tsk_updated_at TIMESTAMP,
    tsk_deleted_at TIMESTAMP,

    prj_id BIGINT NOT NULL REFERENCES projects(prj_id) ON DELETE CASCADE,
    spr_id BIGINT REFERENCES sprints(spr_id) ON DELETE CASCADE
);

CREATE INDEX idx_tasks_fk_prj_id
ON tasks(prj_id);

CREATE INDEX idx_tasks_fk_spr_id
ON tasks(spr_id);

-- tasks_users (bridge)
CREATE TABLE tasks_users(
    tsk_usr_id BIGSERIAL PRIMARY KEY,
    
    tsk_usr_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    tsk_usr_updated_at TIMESTAMP,
    tsk_usr_deleted_at TIMESTAMP,

    tsk_id BIGINT NOT NULL REFERENCES tasks(tsk_id) ON DELETE CASCADE,
    usr_id BIGINT NOT NULL REFERENCES users(usr_id) ON DELETE CASCADE
);

CREATE INDEX idx_tasks_users_fk_tsk_id
ON tasks_users(tsk_id);

CREATE INDEX idx_tasks_users_fk_usr_id
ON tasks_users(usr_id);

CREATE UNIQUE INDEX unq_tasks_users_on_tsk_id_and_usr_id
ON tasks_users(tsk_id, usr_id)
WHERE tsk_usr_deleted_at IS NULL;

-- task_categories
CREATE TABLE task_categories(
    tsc_id BIGSERIAL PRIMARY KEY,

    tsc_name VARCHAR(255) NOT NULL,

    tsc_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    tsc_updated_at TIMESTAMP,
    tsc_deleted_at TIMESTAMP,

    prj_id BIGINT NOT NULL REFERENCES projects(prj_id) ON DELETE CASCADE
);

CREATE INDEX idx_task_categories_fk_prj_id
ON task_categories(prj_id);

-- tasks_task_categories (bridge)
CREATE TABLE tasks_task_categories(
    tsk_tsc_id BIGSERIAL PRIMARY KEY,

    tsk_tsc_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    tsk_tsc_updated_at TIMESTAMP,
    tsk_tsc_deleted_at TIMESTAMP,

    tsk_id BIGINT NOT NULL REFERENCES tasks(tsk_id) ON DELETE CASCADE,
    tsc_id BIGINT NOT NULL REFERENCES task_categories(tsc_id) ON DELETE CASCADE
);

CREATE INDEX idx_tasks_task_categories_fk_tsk_id
ON tasks_task_categories(tsk_id);

CREATE INDEX idx_tasks_task_categories_fk_tsc_id
ON tasks_task_categories(tsc_id);

CREATE UNIQUE INDEX unq_tasks_task_categories_on_tsk_id_and_tsc_id
ON tasks_task_categories(tsk_id,tsc_id)
WHERE tsk_tsc_deleted_at IS NULL;

-- comments
CREATE TABLE comments(
    cmt_id BIGSERIAL PRIMARY KEY,
    cmt_content TEXT,

    cmt_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    cmt_updated_at TIMESTAMP,
    cmt_deleted_at TIMESTAMP,

    usr_id BIGINT NOT NULL REFERENCES users(usr_id) ON DELETE CASCADE,
    tsk_id BIGINT NOT NULL REFERENCES tasks(tsk_id) ON DELETE CASCADE
);

CREATE INDEX idx_comments_fk_usr_id
ON comments(usr_id);

CREATE INDEX idx_comments_fk_tsk_id
ON comments(tsk_id);

-- attachments
CREATE TABLE attachments(
    atc_id BIGSERIAL PRIMARY KEY,

    atc_url TEXT NOT NULL,
    atc_file_name VARCHAR(255) NOT NULL,
    atc_file_size BIGINT,
    atc_mime_type VARCHAR(100),

    atc_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atc_updated_at TIMESTAMP,
    atc_deleted_at TIMESTAMP,

    usr_id BIGINT NOT NULL REFERENCES users(usr_id) ON DELETE CASCADE,
    tsk_id BIGINT NOT NULL REFERENCES tasks(tsk_id) ON DELETE CASCADE,
    cmt_id BIGINT REFERENCES comments(cmt_id)
);

CREATE INDEX idx_attachments_fk_usr_id
ON attachments(usr_id);

CREATE INDEX idx_attachments_fk_tsk_id
ON attachments(tsk_id);

CREATE INDEX idx_attachments_fk_cmt_id
ON attachments(cmt_id);

-- notifications
CREATE TABLE notifications(
    ntf_id BIGSERIAL PRIMARY KEY,

    ntf_content TEXT,
    ntf_is_read BOOLEAN NOT NULL DEFAULT false,
    ntf_read_at TIMESTAMP,
    ntf_level NOTIFICATION_LEVEL NOT NULL DEFAULT 'alert',

    ntf_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ntf_updated_at TIMESTAMP,
    ntf_deleted_at TIMESTAMP,

    usr_id BIGINT NOT NULL REFERENCES users(usr_id) ON DELETE CASCADE
);

CREATE INDEX idx_notifications_fk_usr_id
ON notifications(usr_id);

-- activity_logs
CREATE TABLE activity_logs(
    act_id BIGSERIAL PRIMARY KEY,

    act_action VARCHAR(255) NOT NULL,
    act_details TEXT NOT NULL,

    act_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    act_updated_at TIMESTAMP,
    act_deleted_at TIMESTAMP,

    usr_id BIGINT NOT NULL REFERENCES users(usr_id) ON DELETE CASCADE
);

CREATE INDEX idx_activity_logs_fk_usr_id
ON activity_logs(usr_id);

-- work_times
CREATE TABLE work_times(
    wkt_id BIGSERIAL PRIMARY KEY,

    wkt_hours DECIMAL NOT NULL DEFAULT 0.00,
    wkt_date DATE NOT NULL,
    wkt_description TEXT,

    wkt_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    wkt_updated_at TIMESTAMP,
    wkt_deleted_at TIMESTAMP,

    usr_id BIGINT NOT NULL REFERENCES users(usr_id) ON DELETE CASCADE,
    tsk_id BIGINT NOT NULL REFERENCES tasks(tsk_id) ON DELETE CASCADE
);

CREATE INDEX idx_work_times_fk_usr_id
ON work_times(usr_id);

CREATE INDEX idx_work_times_fk_tsk_id
ON work_times(tsk_id);

