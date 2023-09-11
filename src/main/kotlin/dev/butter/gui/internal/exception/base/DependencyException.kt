package dev.butter.gui.internal.exception.base

import dev.butter.gui.internal.AnyClass

internal abstract class DependencyException(error: String) : RuntimeException(error) {
    abstract val dependency: AnyClass
}