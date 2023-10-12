package dev.butter.gui.api.item.types

import org.bukkit.inventory.ItemStack

/**
 * Represents a GUI item.
 */
internal sealed class GuiItem(
    open var stack: ItemStack,
)