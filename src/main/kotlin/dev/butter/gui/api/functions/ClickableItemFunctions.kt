package dev.butter.gui.api.functions

import dev.butter.gui.api.base.GuiContents
import dev.butter.gui.api.item.types.ClickableItem
import dev.butter.gui.api.item.types.ClickAction
import dev.butter.gui.internal.validation.*
import org.bukkit.inventory.ItemStack

/**
 * Sets the slot provided to the given item and action.
 * This item will be clickable.
 *
 * @param slot The slot to set.
 * @param item The item to set with.
 * @param action The action to perform when clicked.
 */
@NonPageSpecific
fun GuiContents.set(
    slot: Int,
    item: ItemStack,
    action: ClickAction,
) {
    checkSlots(slot)

    this.contentItems += slot to ClickableItem(item, action)
}

/**
 * Sets the row and column provided to the given item and action.
 * This item will be clickable.
 *
 * @param row The row to set.
 * @param column The column to set.
 * @param item The item to set with.
 * @param action The action to perform when clicked.
 */
@NonPageSpecific
fun GuiContents.set(
    row: Int,
    column: Int,
    item: ItemStack,
    action: ClickAction,
) {
    checkRows(row)
    checkColumns(column)

    set(slotOf(row, column), item, action)
}

/**
 * Sets the rows and column provided to the given item and action.
 * This item will be clickable.
 *
 * @param rows The rows to set.
 * @param column The column to set.
 * @param item The item to set with.
 * @param action The action to perform when clicked.
 */
@NonPageSpecific
fun GuiContents.set(
    rows: IntRange,
    column: Int,
    item: ItemStack,
    action: ClickAction,
) {
    checkRowRange(rows)
    checkColumns(column)

    rows.forEach { row ->
        set(row, column, item, action)
    }
}

/**
 * Sets the row and columns provided to the given item and action.
 * This item will be clickable.
 *
 * @param row The row to set.
 * @param columns The columns to set.
 * @param item The item to set with.
 * @param action The action to perform when clicked.
 */
@NonPageSpecific
fun GuiContents.set(
    row: Int,
    columns: IntRange,
    item: ItemStack,
    action: ClickAction,
) {
    checkRows(row)
    checkColumnRange(columns)

    columns.forEach { column ->
        set(row, column, item, action)
    }
}

/**
 * Sets the rows and columns provided to the given item and action.
 * This item will be clickable.
 *
 * @param rows The rows to set.
 * @param columns The columns to set.
 * @param item The item to set with.
 * @param action The action to perform when clicked.
 */
@NonPageSpecific
fun GuiContents.set(
    rows: IntRange,
    columns: IntRange,
    item: ItemStack,
    action: ClickAction,
) {
    checkRowRange(rows)
    checkColumnRange(columns)

    rows.forEach { row ->
        columns.forEach { column ->
            set(row, column, item, action)
        }
    }
}

/**
 * Sets the slots provided to the given item and action.
 * This item will be clickable.
 *
 * @param slots The slots to set.
 * @param item The item to set with.
 * @param action The action to perform when clicked.
 */
@NonPageSpecific
fun GuiContents.set(
    slots: IntRange,
    item: ItemStack,
    action: ClickAction,
) {
    checkSlotRange(slots)

    slots.forEach { slot ->
        set(slot, item, action)
    }
}