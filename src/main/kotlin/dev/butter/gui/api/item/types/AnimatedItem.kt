package dev.butter.gui.api.item.types

import org.bukkit.inventory.ItemStack

/**
 * An [Animated] [GuiItem].
 */
internal data class AnimatedItem(
    override val tickSpeed: Long,
    override val cycleItems: List<ItemStack>,
    override var stack: ItemStack,
) : GuiItem(stack), Animated