package dev.butter.gui.api.item.types

import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemStack

/**
 * Represents a function that provides a Player object.
 */
internal typealias PlayerAction = (Player) -> Unit
internal val DefaultPlayerAction: PlayerAction = {}

/**
 * Represents a function that provides a Player object and the ClickType of an inventory click.
 */
internal typealias ClickAction = (Player, ClickType) -> Unit
internal val DefaultClickAction: ClickAction = { _, _ -> }

/**
 * Represents a function that provides a Player object, the ClickType of an inventory click, and the ItemStack clicked.
 */
internal typealias PageClickAction = (Player, ClickType, ItemStack) -> Unit
internal val DefaultPageClickAction: PageClickAction = { _, _, _ -> }

/**
 * Represents a function used in page partitions to filter out certain items.
 */
internal typealias PageFilter = (ItemStack) -> Boolean
internal val DefaultPageFilter: PageFilter = { true }