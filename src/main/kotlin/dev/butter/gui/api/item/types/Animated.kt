package dev.butter.gui.api.item.types

import org.bukkit.inventory.ItemStack

/**
 * Represents an item type that cycles through a list of items.
 */
internal sealed interface Animated {
    val tickSpeed: Long
    val cycleItems: List<ItemStack>
}