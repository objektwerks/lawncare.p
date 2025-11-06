package lawncare.pane

import scalafx.Includes.*
import scalafx.geometry.Insets
import scalafx.scene.control.{Button, SelectionMode, Tab, TabPane, TableColumn, TableView}
import scalafx.scene.layout.{HBox, Priority, VBox}

import lawncare.{Context, Model, Property}
import lawncare.dialog.PropertyDialog

final class PropertiesPane(context: Context, model: Model) extends VBox:
  spacing = 6
  padding = Insets(6)

  val tableView = new TableView[Property]():
    columns ++= List(
      new TableColumn[Property, String]:
        text = context.headerLocation
        cellValueFactory = _.value.locationProperty
    )
    items = model.observableProperties
  tableView.columnResizePolicy = TableView.ConstrainedResizePolicy

  val addButton = new Button:
    graphic = context.addImage
    text = context.buttonAdd
    disable = false
    onAction = { _ => add() }

  val editButton = new Button:
    graphic = context.editImage
    text = context.buttonEdit
    disable = true
    onAction = { _ => update() }

  val buttonBar = new HBox:
    spacing = 6
    children = List(addButton, editButton)

  val tab = new Tab:
  	text = context.tabProperties
  	closable = false
  	content = new VBox {
      spacing = 6
      padding = Insets(6)
      children = List(tableView, buttonBar)
    }

  val tabPane = new TabPane:
    tabs = List(tab)

  children = List(tabPane)
  VBox.setVgrow(tableView, Priority.Always)
  VBox.setVgrow(tabPane, Priority.Always)

  tableView.onMouseClicked = { event =>
    if (event.getClickCount == 2 && tableView.selectionModel().getSelectedItem != null) update()
  }

  tableView.selectionModel().selectionModeProperty.value = SelectionMode.Single
  
  tableView.selectionModel().selectedItemProperty().addListener { (_, _, selectedItem) =>
    // model.update executes a remove and add on items. the remove passes a null selectedItem!
    if selectedItem != null then
      model.selectedPropertyId.value = selectedItem.id
      editButton.disable = false
    else editButton.disable = true
  }

  def add(): Unit =
    PropertyDialog(context, Property(location = "")).showAndWait() match
      case Some(property: Property) => model.add(property) {
        tableView.selectionModel().select(property.copy(id = model.selectedPropertyId.value))
      }
      case _ =>

  def update(): Unit =
    if tableView.selectionModel().getSelectedItem != null then
      val selectedIndex = tableView.selectionModel().getSelectedIndex
      val property = tableView.selectionModel().getSelectedItem.property
      PropertyDialog(context, property).showAndWait() match
        case Some(property: Property) => model.update(selectedIndex, property) {
          tableView.selectionModel().select(selectedIndex)
        }
        case _ =>