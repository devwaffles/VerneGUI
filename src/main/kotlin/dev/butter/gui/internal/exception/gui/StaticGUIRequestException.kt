package dev.butter.gui.internal.exception.gui

import dev.butter.gui.internal.exception.base.GUIException
import dev.butter.gui.internal.types.GUIClass

internal class StaticGUIRequestException(
    override val gui: GUIClass
) : GUIException(
    "GUI: ${gui.simpleName} is a static gui class, input a (dynamic) player dependent gui instead."
)
