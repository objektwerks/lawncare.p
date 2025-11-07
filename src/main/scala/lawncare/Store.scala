package lawncare

import com.zaxxer.hikari.HikariDataSource

import javax.sql.DataSource

import scalikejdbc.*

final class Store(context: Context):
  private val dataSource: DataSource =
    val ds = new HikariDataSource()
    ds.setDataSourceClassName(context.dataSourceClassName)
    ds.addDataSourceProperty("url", context.url)
    ds.addDataSourceProperty("user", context.user)
    ds.addDataSourceProperty("password", context.password)
    ds.setMaximumPoolSize(context.maximumPoolSize)
    ds

  ConnectionPool.singleton(DataSourceConnectionPool(dataSource))

  def listProperties(): List[Property] =
    DB readOnly { implicit session =>
      sql"select * from property order by location"
        .map(rs =>
          Property(
            rs.long("id"),
            rs.string("location"), 
            rs.string("added")
          )
        )
        .list()
    }

  def addProperty(property: Property): Long =
    DB localTx { implicit session =>
      sql"""
        insert into property(account_id, location, added) values(${property.location}, ${property.added})
        """
        .updateAndReturnGeneratedKey()
    }

  def updateProperty(property: Property): Long =
    DB localTx { implicit session =>
      sql"""
        update property set location = ${property.location}
        where id = ${property.id}
        """
        .update()
      property.id
    }

  def listSessions(propertyId: Long): List[Session] =
    DB readOnly { implicit session =>
      sql"select * from session where property_id = $propertyId order by occurred desc"
        .map(rs =>
          Session(
            rs.long("id"),
            rs.long("property_id"),
            rs.boolean("mowed"),
            rs.boolean("edged"),
            rs.boolean("trimmed"),
            rs.boolean("blowed"),
            rs.boolean("fertilized"),
            rs.boolean("pesticided"),
            rs.boolean("weeded"),
            rs.boolean("watered"),
            rs.boolean("repaired"),
            rs.string("note"),
            rs.string("occurred")
          )
        )
        .list()
    }

  def addSession(sess: Session): Long =
    DB localTx { implicit session =>
      sql"""
        insert into session(property_id, mowed, edged, trimmed, blowed, fertilized, pesticided, weeded, watered, repaired, note, occurred)
        values(${sess.propertyId}, ${sess.mowed}, ${sess.edged}, ${sess.trimmed}, ${sess.blowed}, ${sess.fertilized}, ${sess.pesticided},
        ${sess.weeded}, ${sess.watered}, ${sess.repaired}, ${sess.note}, ${sess.occurred})
        """
        .updateAndReturnGeneratedKey()
    }

  def updateSession(sess: Session): Long =
    DB localTx { implicit session =>
      sql"""
        update session set mowed = ${sess.mowed}, edged = ${sess.edged}, trimmed = ${sess.trimmed}, blowed = ${sess.blowed},
        fertilized = ${sess.fertilized}, pesticided = ${sess.pesticided}, weeded = ${sess.weeded}, watered = ${sess.watered},
        repaired = ${sess.repaired}, note = ${sess.note}, occurred = ${sess.occurred} where id = ${sess.id}
        """
        .update()
      sess.id
    }

  def listIssues(propertyId: Long): List[Issue] =
    DB readOnly { implicit session =>
      sql"select * from issue where property_id = $propertyId order by reported desc"
        .map(rs =>
          Issue(
            rs.long("id"),
            rs.long("property_id"),
            rs.string("report"),
            rs.string("resolution"),
            rs.string("reported"),
            rs.string("resolved")
          )
        )
        .list()
    }

  def addIssue(issue: Issue): Long =
    DB localTx { implicit session =>
      sql"""
        insert into issue(property_id, report, resolution, reported, resolved)
        values(${issue.propertyId}, ${issue.report}, ${issue.resolution}, ${issue.reported}, ${issue.resolved})
        """
        .updateAndReturnGeneratedKey()
    }

  def updateIssue(issue: Issue): Long =
    DB localTx { implicit session =>
      sql"""
        update issue set report = ${issue.report}, resolution = ${issue.resolution}, reported = ${issue.reported},
        resolved = ${issue.resolved} where id = ${issue.id}
        """
        .update()
      issue.id
    }

  def isIssueResolved(issue: Issue): Boolean =
    DB localTx { implicit session =>
      sql"""
        select resolved from issue where id = ${issue.id}
        """
        .map(rs => rs.string("resolved"))
        .single()
        .fold(false)(resolved => resolved != issue.resolved)
    }