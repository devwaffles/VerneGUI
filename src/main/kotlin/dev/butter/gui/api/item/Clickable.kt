package dev.butter.gui.api.item

import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent

internal typealias InventoryAction = (Player, InventoryClickEvent) -> Unit

sealed interface Clickable {
    val action: InventoryAction
}