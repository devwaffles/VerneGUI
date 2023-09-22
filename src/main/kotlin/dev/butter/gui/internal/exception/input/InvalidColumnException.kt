package dev.butter.gui.internal.exception.input

import dev.butter.gui.internal.exception.base.GUIException
import dev.butter.gui.internal.types.GUIClass
import dev.butter.gui.internal.validation.RangeConstants.DEFAULT_COLUMNS

internal class InvalidColumnException(
    override val gui: GUIClass,
    input: Int,
) : GUIException(
    "Invalid column for GUI: ${gui.simpleName} - " +
    "Input: $input -> Column must be within $DEFAULT_COLUMNS."
)