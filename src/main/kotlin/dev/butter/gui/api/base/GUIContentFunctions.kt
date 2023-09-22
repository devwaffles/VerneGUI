package dev.butter.gui.api.base

import dev.butter.gui.api.item.*
import dev.butter.gui.internal.validation.*
import org.bukkit.inventory.ItemStack

fun GUIContents.fill(item: ItemStack) =
    this.slotRange.forEach { slot ->
        if (this.items.any { it.slot == slot }) {
            return@forEach
        }

        this.set(slot, item)
    }

fun GUIContents.set(
    row: Int,
    column: Int,
    item: ItemStack,
) {
    checkRows(row)
    checkColumns(column)

    this.items += RegularItem(row, column, item)
}

fun GUIContents.set(
    rows: IntRange,
    column: Int,
    item: ItemStack
) {
    checkRowRange(rows)
    checkColumns(column)

    rows.forEach { row ->
        this.items += RegularItem(row, column, item)
    }
}

fun GUIContents.set(
    row: Int,
    columns: IntRange,
    item: ItemStack,
) {
    checkRows(row)
    checkColumnRange(columns)

    columns.forEach { column ->
        this.items += RegularItem(row, column, item)
    }
}

fun GUIContents.set(
    slot: Int,
    item: ItemStack,
) {
    checkSlots(slot)

    this.items += RegularItem(slot, item)
}

fun GUIContents.set(
    slots: IntRange,
    item: ItemStack,
) {
    checkSlotRange(slots)

    slots.forEach { slot ->
        this.items += RegularItem(slot, item)
    }
}

fun GUIContents.setClickable(
    row: Int,
    column: Int,
    item: ItemStack,
    action: InventoryAction,
) {
    checkRows(row)
    checkColumns(column)

    this.items += ClickableItem(row, column, item, action)
}

fun GUIContents.setClickable(
    slot: Int,
    item: ItemStack,
    action: InventoryAction,
) {
    checkSlots(slot)

    this.items += ClickableItem(slot, item, action)
}

fun GUIContents.setAnimated(
    row: Int,
    column: Int,
    tickSpeed: Long,
    vararg cycleItems: ItemStack
) {
    checkRows(row)
    checkColumns(column)
    checkDelays(tickSpeed)

    this.items += AnimatedItem(row, column, tickSpeed, *cycleItems)
}

fun GUIContents.setAnimated(
    rows: IntRange,
    column: Int,
    tickSpeed: Long,
    vararg cycleItems: ItemStack
) {
    checkRowRange(rows)
    checkColumns(column)
    checkDelays(tickSpeed)

    rows.forEach { row ->
        this.items += AnimatedItem(row, column, tickSpeed, *cycleItems)
    }
}

fun GUIContents.setAnimated(
    row: Int,
    columns: IntRange,
    tickSpeed: Long,
    vararg cycleItems: ItemStack
) {
    checkRows(row)
    checkColumnRange(columns)
    checkDelays(tickSpeed)

    columns.forEach { column ->
        this.items += AnimatedItem(row, column, tickSpeed, *cycleItems)
    }
}

fun GUIContents.setAnimated(
    slot: Int,
    tickSpeed: Long,
    vararg cycleItems: ItemStack
) {
    checkSlots(slot)
    checkDelays(tickSpeed)

    this.items += AnimatedItem(slot, tickSpeed, *cycleItems)
}

fun GUIContents.setAnimatedClickable(
    row: Int,
    column: Int,
    tickSpeed: Long,
    cycleItems: List<ItemStack>,
    action: InventoryAction,
) {
    checkRows(row)
    checkColumns(column)
    checkDelays(tickSpeed)

    this.items += AnimatedClickableItem(row, column, tickSpeed, cycleItems, action)
}

fun GUIContents.setAnimatedClickable(
    slot: Int,
    tickSpeed: Long,
    cycleItems: List<ItemStack>,
    action: InventoryAction,
) {
    checkSlots(slot)
    checkDelays(tickSpeed)

    this.items += AnimatedClickableItem(slot, tickSpeed, cycleItems, action)
}