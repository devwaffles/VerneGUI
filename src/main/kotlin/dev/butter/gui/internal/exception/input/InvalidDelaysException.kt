package dev.butter.gui.internal.exception.input

import dev.butter.gui.internal.GUIClass
import dev.butter.gui.internal.exception.base.GUIException
import dev.butter.gui.internal.validation.RangeConstants.DEFAULT_DELAYS

internal class InvalidDelaysException(
    override val gui: GUIClass,
    delay: Long
) : GUIException(
    "Invalid delay for GUI: ${gui.simpleName} - " +
    "Input: $delay -> Delay must be within $DEFAULT_DELAYS."
)
