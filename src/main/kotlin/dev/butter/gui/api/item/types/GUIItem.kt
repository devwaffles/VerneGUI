package dev.butter.gui.api.item.types

import org.bukkit.inventory.ItemStack

sealed class GUIItem(
    open var item: ItemStack,
)