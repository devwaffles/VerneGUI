package dev.butter.gui.api.annotation

import dev.butter.gui.api.base.BaseGui
import dev.butter.gui.api.functions.partition
import dev.butter.gui.internal.validation.NonPageSpecific
import dev.butter.gui.internal.validation.PageSpecific

/**
 * Marks a function as the default contents of a [BaseGui].
 * This function will be called when the gui is opened,
 * whenever the contents are updated, a clickable item is
 * clicked, or the gui is refreshed. This function should
 * be used to set up the contents of the gui. Notably, this
 * function is called before any other functions in the gui,
 * and should be used to call any partition function calls.
 * No parameters will be passed to this function.
 *
 * Only content function calls marked with the [NonPageSpecific]
 * annotation should be used in the function annotated with this annotation.
 * Using content functions annotated with the [PageSpecific]
 * annotation will likely result in erroneous behavior.
 *
 * @see partition
 * @see BaseGui
 * @see PageContents
 * @see NonPageSpecific
 * @see PageSpecific
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class DefaultContents