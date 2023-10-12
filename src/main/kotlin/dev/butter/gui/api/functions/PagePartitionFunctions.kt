package dev.butter.gui.api.functions

import dev.butter.gui.api.base.GuiContents
import dev.butter.gui.api.item.builder.item
import dev.butter.gui.api.item.types.DefaultPageInventoryAction
import dev.butter.gui.api.item.types.PageAction.NONE
import dev.butter.gui.api.item.types.PageInventoryAction
import dev.butter.gui.api.item.types.PageItem
import dev.butter.gui.internal.validation.*

/**
 * Marks a slot to be filled with any page item.
 * This item will be clickable.
 *
 * @param slot The slot to fill.
 * @param action The action to perform when the item is clicked.
 */
@NonPageSpecific
fun GuiContents.partition(
    slot: Int,
    action: PageInventoryAction = DefaultPageInventoryAction,
) {
    checkSlots(slot)

    this.contentItems += slot to PageItem(item(), NONE, action)
}

/**
 * Marks a row and column to be filled with any page item.
 * This item will be clickable.
 *
 * @param row The row to fill.
 * @param column The column to fill.
 * @param action The action to perform when the item is clicked.
 */
@NonPageSpecific
fun GuiContents.partition(
    row: Int,
    column: Int,
    action: PageInventoryAction = DefaultPageInventoryAction,
) {
    checkRows(row)
    checkColumns(column)

    partition(slotOf(row, column), action)
}

/**
 * Marks a row and column range to be filled with any page item.
 * These items will be clickable.
 *
 * @param row The row to fill.
 * @param columns The column range to fill.
 * @param action The action to perform when the item is clicked.
 */
@NonPageSpecific
fun GuiContents.partition(
    row: Int,
    columns: IntRange,
    action: PageInventoryAction = DefaultPageInventoryAction,
) {
    checkRows(row)
    checkColumnRange(columns)

    columns.forEach { column ->
        partition(row, column, action)
    }
}

/**
 * Marks a row range and column to be filled with any page item.
 * These items will be clickable.
 *
 * @param rows The row range to fill.
 * @param column The column to fill.
 * @param action The action to perform when the item is clicked.
 */
@NonPageSpecific
fun GuiContents.partition(
    rows: IntRange,
    column: Int,
    action: PageInventoryAction = DefaultPageInventoryAction,
) {
    checkRowRange(rows)
    checkColumns(column)

    rows.forEach { row ->
        partition(row, column, action)
    }
}

/**
 * Marks a row range and column range to be filled with any page item.
 * These items will be clickable.
 *
 * @param rows The row range to fill.
 * @param columns The column range to fill.
 * @param action The action to perform when the item is clicked.
 */
@NonPageSpecific
fun GuiContents.partition(
    rows: IntRange,
    columns: IntRange,
    action: PageInventoryAction = DefaultPageInventoryAction,
) {
    checkRowRange(rows)
    checkColumnRange(columns)

    rows.forEach { row ->
        columns.forEach { column ->
            partition(row, column, action)
        }
    }
}

/**
 * Marks a slot range to be filled with any page item.
 * These items will be clickable.
 *
 * @param slots The slot range to fill.
 * @param action The action to perform when the item is clicked.
 */
@NonPageSpecific
fun GuiContents.partition(
    slots: IntRange,
    action: PageInventoryAction = DefaultPageInventoryAction,
) {
    checkSlotRange(slots)

    slots.forEach { slot ->
        partition(slot, action)
    }
}