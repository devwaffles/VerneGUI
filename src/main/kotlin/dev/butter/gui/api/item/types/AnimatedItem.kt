package dev.butter.gui.api.item.types

import org.bukkit.inventory.ItemStack

data class AnimatedItem
internal constructor(
    override val tickSpeed: Long,
    override val cycleItems: List<ItemStack>,
    override val startingItem: ItemStack = cycleItems.first(),
) : GUIItem(startingItem), Animated