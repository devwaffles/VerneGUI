package dev.butter.gui.api.functions

import dev.butter.gui.api.base.GuiContents
import dev.butter.gui.api.item.builder.item
import dev.butter.gui.api.item.types.*
import dev.butter.gui.api.item.types.PageAction.NONE
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
    filter: PageFilter = DefaultPageFilter,
    action: PageClickAction = DefaultPageClickAction,
) {
    checkSlots(slot)
    checkPartitionOverlap(slot)

    this.contentItems += slot to PageItem(item(), NONE, filter, action)
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
    filter: PageFilter = DefaultPageFilter,
    action: PageClickAction = DefaultPageClickAction,
) {
    checkRows(row)
    checkColumns(column)

    partition(slotOf(row, column), filter, action)
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
    filter: PageFilter = DefaultPageFilter,
    action: PageClickAction = DefaultPageClickAction,
) {
    checkRows(row)
    checkColumnRange(columns)

    columns.forEach { column ->
        partition(row, column, filter, action)
    }
}

/**
 * Marks a row and column collection to be filled with any page item.
 *
 * @param row The row to fill.
 * @param columns The column collection to fill.
 * @param action The action to perform when the item is clicked.
 */
@NonPageSpecific
fun GuiContents.partition(
    row: Int,
    columns: Collection<Int>,
    filter: PageFilter = DefaultPageFilter,
    action: PageClickAction = DefaultPageClickAction,
) {
    checkRows(row)
    checkColumns(columns)

    columns.forEach { column ->
        partition(row, column, filter, action)
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
    filter: PageFilter = DefaultPageFilter,
    action: PageClickAction = DefaultPageClickAction,
) {
    checkRowRange(rows)
    checkColumns(column)

    rows.forEach { row ->
        partition(row, column, filter, action)
    }
}

/**
 * Marks a row collection and column to be filled with any page item.
 * These items will be clickable.
 *
 * @param rows The row collection to fill.
 * @param column The column to fill.
 * @param action The action to perform when the item is clicked.
 */
@NonPageSpecific
fun GuiContents.partition(
    rows: Collection<Int>,
    column: Int,
    filter: PageFilter = DefaultPageFilter,
    action: PageClickAction = DefaultPageClickAction,
) {
    checkRows(rows)
    checkColumns(column)

    rows.forEach { row ->
        partition(row, column, filter, action)
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
    filter: PageFilter = DefaultPageFilter,
    action: PageClickAction = DefaultPageClickAction,
) {
    checkRowRange(rows)
    checkColumnRange(columns)

    rows.forEach { row ->
        columns.forEach { column ->
            partition(row, column, filter, action)
        }
    }
}

/**
 * Marks a row and column collection to be filled with any page item.
 *
 * @param rows The row collection to fill.
 * @param columns The column collection to fill.
 * @param action The action to perform when the item is clicked.
 */
@NonPageSpecific
fun GuiContents.partition(
    rows: Collection<Int>,
    columns: Collection<Int>,
    filter: PageFilter = DefaultPageFilter,
    action: PageClickAction = DefaultPageClickAction,
) {
    checkRows(rows)
    checkColumns(columns)

    rows.forEach { row ->
        columns.forEach { column ->
            partition(row, column, filter, action)
        }
    }
}

/**
 * Marks a row collection and column range to be filled with any page item.
 *
 * @param rows The row collection to fill.
 * @param columns The column range to fill.
 * @param action The action to perform when the item is clicked.
 */
@NonPageSpecific
fun GuiContents.partition(
    rows: Collection<Int>,
    columns: IntRange,
    filter: PageFilter = DefaultPageFilter,
    action: PageClickAction = DefaultPageClickAction,
) {
    checkRows(rows)
    checkColumnRange(columns)

    rows.forEach { row ->
        columns.forEach { column ->
            partition(row, column, filter, action)
        }
    }
}

/**
 * Marks a row range and column collection to be filled with any page item.
 *
 * @param rows The row range to fill.
 * @param columns The column collection to fill.
 * @param action The action to perform when the item is clicked.
 */
@NonPageSpecific
fun GuiContents.partition(
    rows: IntRange,
    columns: Collection<Int>,
    filter: PageFilter = DefaultPageFilter,
    action: PageClickAction = DefaultPageClickAction,
) {
    checkRowRange(rows)
    checkColumns(columns)

    rows.forEach { row ->
        columns.forEach { column ->
            partition(row, column, filter, action)
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
    filter: PageFilter = DefaultPageFilter,
    action: PageClickAction = DefaultPageClickAction,
) {
    checkSlotRange(slots)

    slots.forEach { slot ->
        partition(slot, filter, action)
    }
}

/**
 * Marks a slot collection to be filled with any page item.
 * These items will be clickable.
 *
 * @param slots The slot collection to fill.
 * @param action The action to perform when the item is clicked.
 */
@NonPageSpecific
fun GuiContents.partition(
    slots: Collection<Int>,
    filter: PageFilter = DefaultPageFilter,
    action: PageClickAction = DefaultPageClickAction,
) {
    checkSlots(slots)

    slots.forEach { slot ->
        partition(slot, filter, action)
    }
}