package dev.butter.gui.api.base

import dev.butter.gui.api.item.GUIItem
import org.bukkit.inventory.Inventory
import kotlin.reflect.KClass

data class GUIContents(
    val guiClass: KClass<out VerneBaseGUI>,
    val inventory: Inventory,
    val items: MutableList<GUIItem> = mutableListOf(),
)