package dev.butter.gui.api.item

import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent

internal typealias InventoryAction = (Player, ClickType) -> Unit

sealed interface Clickable {
    val action: InventoryAction
}