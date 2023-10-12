package dev.butter.gui.api.annotation

import dev.butter.gui.api.base.BaseGui
import dev.butter.gui.api.type.GuiType

/**
 * This annotation is used to specify the
 * GUI type. This annotation is required
 * on all registered [BaseGui] classes.
 * The type is used to determine whether the
 * GUI is static or dynamic.
 *
 * @see GuiType
 * @see BaseGui
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class TypeAlias(val type: GuiType)