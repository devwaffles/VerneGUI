package dev.butter.gui.api.item

import org.bukkit.inventory.ItemStack

data class AnimatedItem(
    override val row: Int,
    override val column: Int,
    override val slot: Int,
    override val tickSpeed: Long,
    override val cycleItems: List<ItemStack>,
    override val currentIndex: Int = 0,
) : GUIItem(slot, cycleItems.first()), Animated {
    constructor(
        row: Int,
        column: Int,
        tickSpeed: Long,
        vararg cycleItems: ItemStack
    ) : this(
        row,
        column,
        ((row - 1) * 9) + (column - 1),
        tickSpeed,
        cycleItems.toList(),
    )

    constructor(
        slot: Int,
        tickSpeed: Long,
        vararg cycleItems: ItemStack
    ) : this(
        (slot - slot % 9) / 9,
        (slot % 9) + 1,
        slot,
        tickSpeed,
        cycleItems.toList(),
    )
}
