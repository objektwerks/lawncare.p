package lawncare

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging

import java.awt.{Taskbar, Toolkit}
import java.awt.Taskbar.Feature

import scalafx.application.JFXApp3

import lawncare.dialog.Alerts

object App extends JFXApp3 with LazyLogging:
  val conf = ConfigFactory.load("app.conf")
  val context = Context(conf)
  val store = Store(context)
  val model = Model(store)

  override def start(): Unit =
    val view = View(context, model)
    stage = new JFXApp3.PrimaryStage:
      scene = view.scene
      title = context.windowTitle
      minWidth = context.windowWidth
      minHeight = context.windowHeight
      icons.add(context.logo)

    if Taskbar.isTaskbarSupported() then
      val taskbar = Taskbar.getTaskbar()
      if taskbar.isSupported(Feature.ICON_IMAGE) then
        val appIcon = Toolkit.getDefaultToolkit.getImage(this.getClass().getResource("/image/logo.png"))
        taskbar.setIconImage(appIcon)
    
    model.properties()
    
    stage.show()
    logger.info("*** Lawncare started!")

  override def stopApp(): Unit = logger.info("*** Lawncare stopped.")