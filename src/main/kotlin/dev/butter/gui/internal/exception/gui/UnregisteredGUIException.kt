package dev.butter.gui.internal.exception.gui

import dev.butter.gui.internal.GUIClass
import dev.butter.gui.internal.exception.base.GUIException

internal class UnregisteredGUIException(
    override val gui: GUIClass
) : GUIException(
    "GUI: ${gui.simpleName} was not found. Register the gui first."
)