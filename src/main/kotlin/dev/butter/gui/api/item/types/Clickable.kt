package dev.butter.gui.api.item.types

import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType

typealias InventoryAction = (Player, ClickType) -> Unit

sealed interface Clickable {
    val action: InventoryAction
}