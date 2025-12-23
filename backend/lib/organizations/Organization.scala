package com.taskmanagement.organizations.demo1

import dependencies

case class Organization(
    column: type, // DEFALUT
    tbl_created_at: LocalDateTime, // DEFAULT CURRENT_TIMESTAMP
    tbl_updated_at: Option[LocalDateTime],
    tbl_deleted_at: Option[LocalDateTime]
)
