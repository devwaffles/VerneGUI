package dev.butter.gui.api.annotation

import dev.butter.gui.api.type.GUIType

/**
 * This annotation is used to specify the
 * GUI type. It is required for all GUIs.
 * This will determine whether the GUI is
 * shared between all players or not.
 *
 * @see dev.butter.gui.api.type.GUIType
 * @see dev.butter.gui.api.base.BaseGUI
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class TypeAlias(val type: GUIType)