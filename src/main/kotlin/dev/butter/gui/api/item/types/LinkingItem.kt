package dev.butter.gui.api.item.types

import dev.butter.gui.api.type.GuiClass
import org.bukkit.inventory.ItemStack
import dev.butter.gui.api.base.BaseGui

/**
 * A [GuiItem] that links to another [BaseGui] instance.
 */
internal data class LinkingItem(
    override var stack: ItemStack,
    val linkingGui: GuiClass,
    val action: PlayerAction,
) : GuiItem(stack)