package dev.butter.gui.api.item.types

import org.bukkit.inventory.ItemStack

data class ClickableItem
internal constructor(
    override var item: ItemStack,
    override val action: InventoryAction,
) : GUIItem(item), Clickable