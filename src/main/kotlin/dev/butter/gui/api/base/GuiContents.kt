package dev.butter.gui.api.base

import dev.butter.gui.api.item.types.GuiItem
import org.bukkit.inventory.ItemStack

/**
 * Represents the contents of a [BaseGui].
 */
data class GuiContents
internal constructor(
    val gui: BaseGui,
    internal val contentItems: MutableMap<Int, GuiItem> = mutableMapOf(),
    internal val pageItems: MutableList<ItemStack> = mutableListOf(),
)