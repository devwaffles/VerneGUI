package dev.butter.gui.api.annotation

/**
 * This annotation is used to specify the delay
 * between each click on a clickable item or
 * animated clickable item.
 *
 * By default, the delay is 1 gametick, with the
 * maximum delay being 100 gameticks. If a longer
 * delay is desired, implement a mechanism within
 * the createContents method of the GUI to handle
 * the delay.
 *
 * @see dev.butter.gui.api.base.BaseGUI
 * @see dev.butter.gui.api.base.BaseGUI.createContents
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class ClickDelay(val delay: Long)
