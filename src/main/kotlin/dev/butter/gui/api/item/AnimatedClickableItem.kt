package dev.butter.gui.api.item

import org.bukkit.inventory.ItemStack

data class AnimatedClickableItem(
    override val row: Int,
    override val column: Int,
    override val slot: Int,
    override val tickSpeed: Long,
    override val cycleItems: List<ItemStack>,
    override val action: InventoryAction,
) : GUIItem(slot, cycleItems.first()), Clickable, Animated {
    constructor(
        row: Int,
        column: Int,
        tickSpeed: Long,
        cycleItems: List<ItemStack>,
        action: InventoryAction,
    ) : this(
        row,
        column,
        ((row - 1) * 9) + (column - 1),
        tickSpeed,
        cycleItems,
        action,
    )

    constructor(
        slot: Int,
        tickSpeed: Long,
        cycleItems: List<ItemStack>,
        action: InventoryAction,
    ) : this(
        (slot - slot % 9) / 9,
        (slot % 9) + 1,
        slot,
        tickSpeed,
        cycleItems,
        action
    )
}