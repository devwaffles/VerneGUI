package dev.butter.gui.api.annotation

/**
 * This annotation is used to specify
 * the size of a GUI. The size must be
 * used on the GUI class. The size will
 * determine the number of rows the GUI
 * will have. It must be between 1 and 6.
 *
 * @see dev.butter.gui.api.VerneGUI
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class GUISize(val rows: Int)
