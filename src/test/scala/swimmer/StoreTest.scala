package lawncare

import com.typesafe.config.ConfigFactory

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

final class StoreTest extends AnyFunSuite with Matchers:
  val context = Context( ConfigFactory.load("test.conf") )
  val store = Store(context)

  var property = Property(location = "fred")
  var session = Session(propertyId = 0)
  var issue = Issue(propertyId = 0)

  test("store"):
    addProperty()
    updateProperty()
    listProperties()

    addSession()
    updateSession()
    listSessions()

  def addProperty(): Unit =
    val id = store.addProperty(property)
    id shouldBe 1
    property = property.copy(id = id)
    session = session.copy(propertyId = id)
    issue = issue.copy(propertyId = id)

  def updateProperty(): Unit =
    property = property.copy(location = "fred flintstone")
    store.updateProperty(property)

  def listProperties(): Unit =
    store.listProperties().length shouldBe 1

  def addSession(): Unit =
    store.addSession(session)

  def updateSession(): Unit =
    session = session.copy(fertilized = true)
    store.updateSession(session)

  def listSessions(): Unit =
    store.listSessions(property.id).length shouldBe 1

  def addIssue(): Unit =
    store.addIssue(issue)

  def updateIssue(): Unit =
    issue = issue.copy(resolved = Entity.now)
    store.updateIssue(issue)

  def listIssues(): Unit =
    store.listIssues(property.id).length shouldBe 1