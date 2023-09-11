package dev.butter.gui.internal.exception.input

import dev.butter.gui.internal.GUIClass
import dev.butter.gui.internal.exception.base.GUIException
import dev.butter.gui.internal.validation.RangeConstants.DEFAULT_ROWS

internal class InvalidRowException(
    override val gui: GUIClass,
    input: Int,
) : GUIException(
    "Invalid row for GUI: ${gui.simpleName} - " +
    "Input: $input -> Row must be within $DEFAULT_ROWS, " +
    "either during GUI constructor or setting contents."
)