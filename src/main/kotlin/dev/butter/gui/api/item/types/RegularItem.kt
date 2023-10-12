package dev.butter.gui.api.item.types

import org.bukkit.inventory.ItemStack

/**
 * A regular [GuiItem] that does not have any special functionality.
 */
internal data class RegularItem(
    override var stack: ItemStack
) : GuiItem(stack)