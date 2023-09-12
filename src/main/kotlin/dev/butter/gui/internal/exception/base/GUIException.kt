package dev.butter.gui.internal.exception.base

import dev.butter.gui.internal.GUIClass

internal abstract class GUIException(error: String) : RuntimeException(error) {
    abstract val gui: GUIClass
}