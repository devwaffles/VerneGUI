package dev.butter.gui.api.item.types

import org.bukkit.inventory.ItemStack

data class RegularItem
internal constructor(
    override var item: ItemStack
) : GUIItem(item)