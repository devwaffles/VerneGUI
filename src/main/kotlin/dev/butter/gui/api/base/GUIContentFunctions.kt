package dev.butter.gui.api.base

import dev.butter.gui.api.item.types.*
import dev.butter.gui.internal.validation.*
import org.bukkit.inventory.ItemStack

fun slotOf(row: Int, column: Int) =
    (row - 1) * 9 + (column - 1)

fun GUIContents.fill(item: ItemStack) = this.slotRange
    .filterNot(this.items.keys::contains)
    .forEach { slot -> this.set(slot, item) }

fun GUIContents.set(
    row: Int,
    column: Int,
    item: ItemStack,
) {
    checkRows(row)
    checkColumns(column)

    val slot = slotOf(row, column)
    this.items += slot to RegularItem(item)
}

fun GUIContents.set(
    rows: IntRange,
    column: Int,
    item: ItemStack
) {
    checkRowRange(rows)
    checkColumns(column)

    rows.forEach { row ->
        set(row, column, item)
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
        set(row, column, item)
    }
}

fun GUIContents.set(
    slot: Int,
    item: ItemStack,
) {
    checkSlots(slot)

    this.items += slot to RegularItem(item)
}

fun GUIContents.set(
    slots: IntRange,
    item: ItemStack,
) {
    checkSlotRange(slots)

    slots.forEach { slot ->
        set(slot, item)
    }
}

fun GUIContents.set(
    slot: Int,
    item: ItemStack,
    action: InventoryAction,
) {
    checkSlots(slot)

    this.items += slot to ClickableItem(item, action)
}

fun GUIContents.set(
    row: Int,
    column: Int,
    item: ItemStack,
    action: InventoryAction,
) {
    checkRows(row)
    checkColumns(column)

    set(slotOf(row, column), item, action)
}

fun GUIContents.set(
    slot: Int,
    tickSpeed: Long,
    vararg cycleItems: ItemStack
) {
    checkSlots(slot)
    checkDelays(tickSpeed)

    this.items += slot to AnimatedItem(tickSpeed, cycleItems.toList(), checkAnimated(slot) ?: cycleItems.first())
}

fun GUIContents.set(
    row: Int,
    column: Int,
    tickSpeed: Long,
    vararg cycleItems: ItemStack
) {
    checkRows(row)
    checkColumns(column)
    checkDelays(tickSpeed)

    set(slotOf(row, column), tickSpeed, *cycleItems)
}

fun GUIContents.set(
    rows: IntRange,
    column: Int,
    tickSpeed: Long,
    vararg cycleItems: ItemStack
) {
    checkRowRange(rows)
    checkColumns(column)
    checkDelays(tickSpeed)

    rows.forEach { row ->
        set(row, column, tickSpeed, *cycleItems)
    }
}

fun GUIContents.set(
    row: Int,
    columns: IntRange,
    tickSpeed: Long,
    vararg cycleItems: ItemStack
) {
    checkRows(row)
    checkColumnRange(columns)
    checkDelays(tickSpeed)

    columns.forEach { column ->
        set(row, column, tickSpeed, *cycleItems)
    }
}

fun GUIContents.set(
    slot: Int,
    tickSpeed: Long,
    cycleItems: List<ItemStack>,
    action: InventoryAction,
) {
    checkSlots(slot)
    checkDelays(tickSpeed)

    this.items += slot to AnimatedClickableItem(tickSpeed, cycleItems, action, checkAnimated(slot) ?: cycleItems.first())
}

fun GUIContents.set(
    row: Int,
    column: Int,
    tickSpeed: Long,
    cycleItems: List<ItemStack>,
    action: InventoryAction,
) {
    checkRows(row)
    checkColumns(column)
    checkDelays(tickSpeed)

    set(slotOf(row, column), tickSpeed, cycleItems, action)
}

fun GUIContents.set(
    rows: IntRange,
    column: Int,
    tickSpeed: Long,
    cycleItems: List<ItemStack>,
    action: InventoryAction,
) {
    checkRowRange(rows)
    checkColumns(column)
    checkDelays(tickSpeed)

    rows.forEach { row ->
        set(row, column, tickSpeed, cycleItems, action)
    }
}

fun GUIContents.set(
    row: Int,
    columns: IntRange,
    tickSpeed: Long,
    cycleItems: List<ItemStack>,
    action: InventoryAction,
) {
    checkRows(row)
    checkColumnRange(columns)
    checkDelays(tickSpeed)

    columns.forEach { column ->
        set(row, column, tickSpeed, cycleItems, action)
    }
}

fun GUIContents.close(
    slot: Int,
    item: ItemStack,
) {
    checkSlots(slot)

    this.items += slot to ClickableItem(item) { player, _ -> player.closeInventory() }
}

fun GUIContents.close(
    row: Int,
    column: Int,
    item: ItemStack,
) {
    checkRows(row)
    checkColumns(column)

    close(slotOf(row, column), item)
}

private fun GUIContents.checkAnimated(slot: Int): ItemStack? {
    val duplicate = this.items[slot] ?: return null
    val animated = duplicate as? Animated ?: return null

    if (duplicate.item !in animated.cycleItems) {
        return null
    }

    return duplicate.item
}