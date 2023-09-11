package dev.butter.gui.internal.exception.gui

import dev.butter.gui.internal.GUIClass
import dev.butter.gui.internal.exception.base.GUIException

internal class MissingGUITitleException(
    override val gui: GUIClass
) : GUIException(
    "GUI: ${gui.simpleName} does not have the annotation GUITitle and will not be registered."
)