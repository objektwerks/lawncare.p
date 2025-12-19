package lawncare

import java.time.LocalDate

import scalafx.beans.property.ObjectProperty

sealed trait Entity:
  val id: Long

object Entity:
  def now: String = LocalDate.now.toString
  def localDate(now: String): LocalDate = if now.nonEmpty then LocalDate.parse(now) else LocalDate.now

final case class Property(id: Long = 0,
                          location: String,
                          added: String = Entity.now) extends Entity derives CanEqual:
  val locationProperty = ObjectProperty[String](this, "location", location)
  val property = this

object Property:
  given sortByLocation: Ordering[Property] = Ordering.by[Property, String](property => property.location)

final case class Session(id: Long = 0,
                         propertyId: Long,
                         mowed: Boolean = true,
                         edged: Boolean = true,
                         trimmed: Boolean = false,
                         blowed: Boolean = true,
                         fertilized: Boolean = false,
                         pesticided: Boolean = false,
                         weeded: Boolean = false,
                         watered: Boolean = false,
                         repaired: Boolean = false,
                         note: String = "note",
                         occurred: String = Entity.now) extends Entity derives CanEqual:
  val mowedProperty = ObjectProperty[Boolean](this, "mowed", mowed)
  val edgedProperty = ObjectProperty[Boolean](this, "edged", edged)
  val trimmedProperty = ObjectProperty[Boolean](this, "trimmed", trimmed)
  val blowedProperty = ObjectProperty[Boolean](this, "blowed", blowed)
  val fertilizedProperty = ObjectProperty[Boolean](this, "fertilized", fertilized)
  val pesticidedProperty = ObjectProperty[Boolean](this, "pesticided", pesticided)
  val weededProperty = ObjectProperty[Boolean](this, "weeded", weeded)
  val wateredProperty = ObjectProperty[Boolean](this, "watered", watered)
  val repairedProperty = ObjectProperty[Boolean](this, "repaired", repaired)
  val noteProperty = ObjectProperty[String](this, "note", note)
  val occurredProperty = ObjectProperty[String](this, "occurred", occurred)
  val session = this

object Session:
  given Ordering[Session] = Ordering.by[Session, String](session => session.occurred).reverse

final case class Issue(id: Long = 0,
                       propertyId: Long,
                       report: String = "report",
                       resolution: String = "resolution",
                       reported: String = Entity.now,
                       resolved: String = Entity.now) extends Entity derives CanEqual:
  val reportProperty = ObjectProperty[String](this, "report", report)
  val resolutionProperty = ObjectProperty[String](this, "resolution", resolution)
  val reportedProperty = ObjectProperty[String](this, "reported", reported)
  val resolvedProperty = ObjectProperty[String](this, "resolved", resolved)
  val issue = this

object Issue:
  given Ordering[Issue] = Ordering.by[Issue, String](issue => issue.reported).reverse