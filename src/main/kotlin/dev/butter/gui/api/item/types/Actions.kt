package dev.butter.gui.api.item.types

import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemStack

/**
 * Represents a function that provides a Player object.
 */
typealias PlayerAction = (Player) -> Unit
val DefaultPlayerAction: PlayerAction = {}

/**
 * Represents a function that provides a Player object and the ClickType of an inventory click.
 */
typealias InventoryAction = (Player, ClickType) -> Unit
val DefaultInventoryAction: InventoryAction = { _, _ -> }

/**
 * Represents a function that provides a Player object, the ClickType of an inventory click, and the ItemStack clicked.
 */
typealias PageInventoryAction = (Player, ClickType, ItemStack) -> Unit
val DefaultPageInventoryAction: PageInventoryAction = { _, _, _ -> }