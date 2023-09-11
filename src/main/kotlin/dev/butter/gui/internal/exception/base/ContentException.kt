package dev.butter.gui.internal.exception.base

import dev.butter.gui.api.base.GUIContents

internal abstract class ContentException(error: String) : RuntimeException(error) {
    abstract val contents: GUIContents
    abstract val inputRange: IntRange
}