package dev.butter.gui.api.item.types

import org.bukkit.inventory.ItemStack

/**
 * Represents a [GuiItem] that holds an item from the page items.
 */
internal data class PageItem(
    override var stack: ItemStack,
    val pageAction: PageAction,
    val filter: PageFilter = DefaultPageFilter,
    val action: PageClickAction = DefaultPageClickAction,
) : GuiItem(stack)