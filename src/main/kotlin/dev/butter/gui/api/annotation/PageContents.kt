package dev.butter.gui.api.annotation

import dev.butter.gui.api.base.BaseGui
import dev.butter.gui.internal.validation.NonPageSpecific
import dev.butter.gui.internal.validation.PageSpecific

/**
 * This annotation is used to mark a function as the page contents of a
 * [BaseGui].
 * It will be called when the GUI is opened, whenever a player clicks
 * on a page item with a unique action, or when the GUI is refreshed.
 * This function should not have any parameters, and should be used
 * to add page content items to the GUI. Notably, this function should
 * focus on updating the page contents, not the non-page contents.
 *
 * Content method calls annotated with the [PageSpecific]
 * annotation should be used in this function. Using content method calls
 * annotated with the [NonPageSpecific]
 * annotation in this function will likely result in unexpected behavior.
 *
 * @see BaseGui
 * @see DefaultContents
 * @see PageSpecific
 * @see NonPageSpecific
 *
 * @param autoClear Whether to clear the page contents on refresh.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class PageContents(val autoClear: Boolean = true)