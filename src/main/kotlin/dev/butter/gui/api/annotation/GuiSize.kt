package dev.butter.gui.api.annotation

import dev.butter.gui.api.VerneGui
import dev.butter.gui.api.base.BaseGui

/**
 * This annotation is used to specify
 * the size of a GUI. This annotation
 * must be present on all registered
 * [BaseGui]s. The size will
 * determine the number of rows the GUI
 * will have. It must be between 3 and 6 rows.
 *
 * @see BaseGui
 * @see VerneGui
 *
 * @param rows The number of rows the GUI will have.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class GuiSize(val rows: Int = 3)