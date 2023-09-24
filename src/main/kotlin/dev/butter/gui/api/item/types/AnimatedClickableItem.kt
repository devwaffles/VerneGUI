package dev.butter.gui.api.item.types

import org.bukkit.inventory.ItemStack

data class AnimatedClickableItem
internal constructor(
    override val tickSpeed: Long,
    override val cycleItems: List<ItemStack>,
    override val action: InventoryAction,
    override val startingItem: ItemStack = cycleItems.first(),
) : GUIItem(startingItem), Clickable, Animated