package dev.butter.gui.api.item.types

import org.bukkit.inventory.ItemStack

/**
 * An [Animated] [Clickable] [GuiItem].
 */
internal data class AnimatedClickableItem(
    override val tickSpeed: Long,
    override val cycleItems: List<ItemStack>,
    override val action: ClickAction,
    override var stack: ItemStack,
) : GuiItem(stack), Animated, Clickable