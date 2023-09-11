package dev.butter.gui.api.item

import org.bukkit.inventory.ItemStack

sealed interface Animated {
    val tickSpeed: Long
    val cycleItems: List<ItemStack>
    val currentIndex: Int
}