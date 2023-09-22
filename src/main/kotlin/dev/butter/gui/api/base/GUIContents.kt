package dev.butter.gui.api.base

import dev.butter.gui.api.item.GUIItem

data class GUIContents(
    val gui: BaseGUI,
    val items: MutableList<GUIItem> = mutableListOf(),
)