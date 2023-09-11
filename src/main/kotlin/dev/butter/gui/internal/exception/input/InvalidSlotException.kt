package dev.butter.gui.internal.exception.input

import dev.butter.gui.internal.GUIClass
import dev.butter.gui.internal.exception.base.GUIException
import dev.butter.gui.internal.validation.RangeConstants.DEFAULT_SLOTS

internal class InvalidSlotException(
    override val gui: GUIClass,
    input: Int,
) : GUIException(
    "Invalid slot amount for GUI: ${gui.simpleName} - " +
    "Input: $input -> Slot must be within $DEFAULT_SLOTS or " +
    "GUI constructor requires slot to be a factor of 9."
)