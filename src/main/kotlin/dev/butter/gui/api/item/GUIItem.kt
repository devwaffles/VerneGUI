package dev.butter.gui.api.item

import org.bukkit.inventory.ItemStack

sealed class GUIItem
private constructor(
    open val row: Int,
    open val column: Int,
    open val slot: Int,
    open var item: ItemStack,
) {
    constructor(
        slot: Int,
        item: ItemStack,
    ) : this(
        (slot - slot % 9) / 9,
        (slot % 9) + 1,
        slot,
        item,
    )
}