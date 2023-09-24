package dev.butter.gui.api.annotation

import dev.butter.gui.api.type.GUIType

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class TypeAlias(val type: GUIType)