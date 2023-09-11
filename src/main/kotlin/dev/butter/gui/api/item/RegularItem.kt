package dev.butter.gui.api.item

import org.bukkit.inventory.ItemStack

data class RegularItem(
    override val row: Int,
    override val column: Int,
    override val slot: Int,
    override val item: ItemStack
) : GUIItem(slot, item) {
    constructor(
        row: Int,
        column: Int,
        item: ItemStack,
    ) : this(
        row,
        column,
        ((row - 1) * 9) + (column - 1),
        item
    )

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