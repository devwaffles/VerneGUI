package dev.butter.gui.internal.exception.gui

import dev.butter.gui.internal.GUIClass
import dev.butter.gui.internal.exception.base.GUIException

internal class StaticGUIRequestException(
    override val gui: GUIClass
) : GUIException(
    "GUI: ${gui.simpleName} is a static gui class, input a (dynamic) player dependent gui instead."
)
