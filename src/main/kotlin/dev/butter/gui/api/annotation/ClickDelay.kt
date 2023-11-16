package dev.butter.gui.api.annotation

import dev.butter.gui.api.base.BaseGui
import dev.butter.gui.api.type.GameTicks

/**
 * This annotation is used to specify the delay between clicks on items in a [BaseGui].
 * Any clicks that occur within the delay will be ignored.
 * By default, the delay is 1 gametick (20ms).
 * The maximum delay that can be specified is 200 gameticks (10 seconds).
 *
 * @param delay The delay between clicks in gameticks. (20 gameticks = 1 second)
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class ClickDelay(val delay: GameTicks = 1L)