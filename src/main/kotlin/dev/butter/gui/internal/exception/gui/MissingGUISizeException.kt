package dev.butter.gui.internal.exception.gui

import dev.butter.gui.internal.exception.base.GUIException
import dev.butter.gui.internal.types.GUIClass

internal class MissingGUISizeException(
    override val gui: GUIClass
) : GUIException(
    "GUI: ${gui.simpleName} does not have the annotation GUISize and will not be registered."
)