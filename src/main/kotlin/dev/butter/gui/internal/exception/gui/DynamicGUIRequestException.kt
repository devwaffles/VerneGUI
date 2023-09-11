package dev.butter.gui.internal.exception.gui

import dev.butter.gui.internal.GUIClass
import dev.butter.gui.internal.exception.base.GUIException

internal class DynamicGUIRequestException(
    override val gui: GUIClass
) : GUIException(
    "GUI: ${gui.simpleName} is a dynamic gui class, input a (static) player independent gui instead."
)
