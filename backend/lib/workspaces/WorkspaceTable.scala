package com.taskmanagement.workspaces.demo1

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future
import java.time.{LocalDate, LocalDateTime}

class WorkspaceTable(tag: Tag) extends Table[Workspace](tag, "workspaces") {
        def wks_id = column[Long]("wks_id")

        def wks_name = column[String]("wks_name")
        def wks_description = column[Option[String]]("wks_description")
        def wks_is_archived = column[Boolean]("wks_is_archived")
        def wks_archived_at = column[Option[LocalDateTime]]("wks_archived_at")
        def wks_is_opened = column[Boolean]("wks_is_opened")

        def wks_created_at = column[LocalDateTime]("wks_created_at")
        def wks_updated_at = column[Option[LocalDateTime]]("wks_updated_at")
        def wks_deleted_at = column[Option[LocalDateTime]]("wks_deleted_at")

        def org_id = column[Long]("org_id")

        def usr_id_who_archived = column[Option[Long]]("usr_id_who_archived")

    def * = (
                wks_id,
                wks_name,
                wks_description,
                wks_is_archived,
                wks_archived_at,
                wks_is_opened,
                wks_created_at,
                wks_updated_at,
                wks_deleted_at,
                org_id,
                usr_id_who_archived
    ).mapTo[Workspace]
}