package dev.butter.gui.api.base

import dev.butter.gui.api.item.types.GUIItem

data class GUIContents(
    val gui: BaseGUI,
    val items: MutableMap<Int, GUIItem> = mutableMapOf(),
)