package dev.butter.gui.api.base

import dev.butter.gui.api.item.GUIItem

data class GUIContents(
    val gui: VerneBaseGUI,
    val items: MutableList<GUIItem> = mutableListOf(),
)