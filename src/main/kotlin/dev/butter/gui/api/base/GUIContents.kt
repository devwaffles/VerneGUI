package dev.butter.gui.api.base

import dev.butter.gui.api.item.types.GUIItem

/**
 * Represents the contents of a GUI.
 *
 * This stores the items in a map with the key
 * being the slot of the item and the value being
 * the item itself. This is updated through the
 * functions provided in the GUIContentFunctions.kt
 * file.
 */
data class GUIContents(
    val gui: BaseGUI,
    val items: MutableMap<Int, GUIItem> = mutableMapOf(),
)