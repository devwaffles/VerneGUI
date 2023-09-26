package dev.butter.gui.api.base

import dev.butter.gui.api.item.types.RegularItem
import dev.butter.gui.internal.validation.*
import org.bukkit.inventory.ItemStack

/**
 * Fills the entire GUI with a given item.
 *
 * @param item The item to fill the GUI with.
 */
@NonPageSpecific
fun GUIContents.fill(item: ItemStack) = this.slotRange
    .filterNot(this.constantItems.keys::contains)
    .forEach { slot -> this.set(slot, item) }

/**
 * Sets the row and column provided to the given item.
 *
 * @param row The row to update.
 * @param column The column to update.
 * @param item The item to set with.
 */
@NonPageSpecific
fun GUIContents.set(
    row: Int,
    column: Int,
    item: ItemStack,
) {
    checkRows(row)
    checkColumns(column)

    val slot = slotOf(row, column)
    this.constantItems += slot to RegularItem(item)
}

/**
 * Sets the range of rows and column provided to the given item.
 *
 * @param rows The rows to update.
 * @param column The column to update.
 * @param item The item to set with.
 */
@NonPageSpecific
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

/**
 * Sets the row and range of columns provided to the given item.
 *
 * @param row The row to update.
 * @param columns The columns to update.
 * @param item The item to set with.
 */
@NonPageSpecific
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

/**
 * Sets the slot provided to the given item.
 *
 * @param slot The slot to update.
 * @param item The item to set with.
 */
@NonPageSpecific
fun GUIContents.set(
    slot: Int,
    item: ItemStack,
) {
    checkSlots(slot)

    this.constantItems += slot to RegularItem(item)
}

/**
 * Sets the range of slots provided to the given item.
 *
 * @param slots The slots to update.
 * @param item The item to set with.
 */
@NonPageSpecific
fun GUIContents.set(
    slots: IntRange,
    item: ItemStack,
) {
    checkSlotRange(slots)

    slots.forEach { slot ->
        set(slot, item)
    }
}