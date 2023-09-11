package dev.butter.gui.api.item

import org.bukkit.inventory.ItemStack

data class ClickableItem(
    override val row: Int,
    override val column: Int,
    override val slot: Int,
    override val item: ItemStack,
    override val action: InventoryAction,
) : GUIItem(slot, item), Clickable {
    constructor(
        row: Int,
        column: Int,
        item: ItemStack,
        action: InventoryAction,
    ) : this(
        row,
        column,
        ((row - 1) * 9) + (column - 1),
        item,
        action,
    )

    constructor(
        slot: Int,
        item: ItemStack,
        action: InventoryAction,
    ) : this(
        (slot - slot % 9) / 9,
        (slot % 9) + 1,
        slot,
        item,
        action,
    )
}