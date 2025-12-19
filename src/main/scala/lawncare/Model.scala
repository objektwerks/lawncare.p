package lawncare

import com.typesafe.scalalogging.LazyLogging

import ox.supervised

import scalafx.application.Platform
import scalafx.collections.ObservableBuffer
import scalafx.beans.property.ObjectProperty

final class Model(store: Store) extends LazyLogging:
  def assertInFxThread(message: String, suffix: String = " should be in fx thread!"): Unit =
    require(Platform.isFxApplicationThread, message + suffix)
  def assertNotInFxThread(message: String, suffix: String = " should not be in fx thread!"): Unit =
    require(!Platform.isFxApplicationThread, message + suffix)

  val selectedPropertyId = ObjectProperty[Long](0)
  val selectedSessionId = ObjectProperty[Long](0)
  val selectedIssueId = ObjectProperty[Long](0)

  selectedPropertyId.onChange { (_, _, newPropertyId) =>
    sessions(newPropertyId)
    issues(newPropertyId)
  }

  val observableProperties = ObservableBuffer[Property]()
  val observableSessions = ObservableBuffer[Session]()
  val observableIssues = ObservableBuffer[Issue]()

  logger.info("Model initialized.")

  def properties(): Unit =
    supervised:
      assertNotInFxThread("list properties")
      observableProperties.clear()
      observableProperties ++= store.listProperties()

  def add(property: Property): Unit =
    supervised:
      assertNotInFxThread(s"add property: $property")
      val id = store.addProperty(property)
      observableProperties.insert(0, property.copy(id = id))
      observableProperties.sort()
      selectedPropertyId.set(id)
      logger.info(s"Added property: $property")

  def update(selectedIndex: Int, property: Property): Unit =
    supervised:
      assertNotInFxThread(s"update property from: $selectedIndex to: $property")
      store.updateProperty(property)
      if selectedIndex > -1 then
        observableProperties.update(selectedIndex, property)
        observableProperties.sort()
        logger.info(s"Updated property: $property")
      else
        logger.error(s"Update of property: $property \nfailed due to invalid index: $selectedIndex")

  def sessions(propertyId: Long): Unit =
    supervised:
      assertNotInFxThread(s"list sessions, property id: $propertyId")
      observableSessions.clear()
      observableSessions ++= store.listSessions(propertyId)

  def add(session: Session): Unit =
    supervised:
      assertNotInFxThread(s"add session: $session")
      val id = store.addSession(session)
      observableSessions.insert(0, session.copy(id = id))
      selectedSessionId.set(id)
      logger.info(s"Added session: $session")

  def update(selectedIndex: Int, session: Session): Unit =
    supervised:
      assertNotInFxThread(s"update session from: $selectedIndex to: $session")
      store.updateSession(session)
      if selectedIndex > -1 then
        observableSessions.update(selectedIndex, session)      
        logger.info(s"Updated session: $session")
      else
        logger.error(s"Update of session: $session \nfailed due to invalid index: $selectedIndex")

  def issues(propertyId: Long): Unit =
    supervised:
      assertNotInFxThread(s"list issues, property id: $propertyId")
      observableIssues.clear()
      observableIssues ++= store.listIssues(propertyId)

  def add(issue: Issue): Unit =
    supervised:
      assertNotInFxThread(s"add issue: $issue")
      val id = store.addIssue(issue)
      observableIssues.insert(0, issue.copy(id = id))
      selectedIssueId.set(id)
      logger.info(s"Added issue: $issue")

  def update(selectedIndex: Int, issue: Issue): Unit =
    supervised:
      assertNotInFxThread(s"update issue from: $selectedIndex to: $issue")
      store.updateIssue(issue)
      if selectedIndex > -1 then
        observableIssues.update(selectedIndex, issue)      
        logger.info(s"Updated issue: $issue")
      else
        logger.error(s"Update of issue: $issue \nfailed due to invalid index: $selectedIndex")