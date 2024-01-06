package dev.butter.gui.api.item.types

import org.bukkit.inventory.ItemStack

/**
 * A [Clickable] [GuiItem].
 */
internal data class ClickableItem(
    override var stack: ItemStack,
    override val action: ClickAction = DefaultClickAction,
) : GuiItem(stack), Clickable