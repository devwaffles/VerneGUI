package dev.butter.gui.api.functions

import dev.butter.gui.api.base.GuiContents
import dev.butter.gui.api.item.types.AnimatedClickableItem
import dev.butter.gui.api.item.types.InventoryAction
import dev.butter.gui.internal.validation.*
import org.bukkit.inventory.ItemStack

/**
 * Sets the slot provided to update and cycle every interval
 * specified with the items provided.
 * This item will be clickable.
 *
 * @param slot The slot to set.
 * @param tickSpeed The interval to cycle the items.
 * @param cycleItems The items to cycle through.
 * @param action The action to perform when clicked.
 */
@NonPageSpecific
fun GuiContents.set(
    slot: Int,
    tickSpeed: Long,
    cycleItems: Collection<ItemStack>,
    action: InventoryAction,
) {
    checkSlots(slot)
    checkDelays(tickSpeed)
    checkItemCount(cycleItems)

    this.contentItems += slot to
            AnimatedClickableItem(
                tickSpeed,
                cycleItems.toList(),
                action,
                checkAnimated(slot) ?: cycleItems.first()
            )
}

/**
 * Sets the row and column provided to update and cycle every interval
 * specified with the items provided.
 * This item will be clickable.
 *
 * @param row The row to set.
 * @param column The column to set.
 * @param tickSpeed The interval to cycle the items.
 * @param cycleItems The items to cycle through.
 * @param action The action to perform when clicked.
 */
@NonPageSpecific
fun GuiContents.set(
    row: Int,
    column: Int,
    tickSpeed: Long,
    cycleItems: Collection<ItemStack>,
    action: InventoryAction,
) {
    checkRows(row)
    checkColumns(column)
    checkDelays(tickSpeed)
    checkItemCount(cycleItems)

    set(slotOf(row, column), tickSpeed, cycleItems, action)
}

/**
 * Sets the range of rows and column provided to update and cycle every interval
 * specified with the items provided.
 * This item will be clickable.
 *
 * @param rows The rows to set.
 * @param column The column to set.
 * @param tickSpeed The interval to cycle the items.
 * @param cycleItems The items to cycle through.
 * @param action The action to perform when clicked.
 */
@NonPageSpecific
fun GuiContents.set(
    rows: IntRange,
    column: Int,
    tickSpeed: Long,
    cycleItems: Collection<ItemStack>,
    action: InventoryAction,
) {
    checkRowRange(rows)
    checkColumns(column)
    checkDelays(tickSpeed)
    checkItemCount(cycleItems)

    rows.forEach { row ->
        set(row, column, tickSpeed, cycleItems, action)
    }
}

/**
 * Sets the row and range of columns provided to update and cycle every interval
 * specified with the items provided.
 * This item will be clickable.
 *
 * @param row The row to set.
 * @param columns The columns to set.
 * @param tickSpeed The interval to cycle the items.
 * @param cycleItems The items to cycle through.
 * @param action The action to perform when clicked.
 */
@NonPageSpecific
fun GuiContents.set(
    row: Int,
    columns: IntRange,
    tickSpeed: Long,
    cycleItems: Collection<ItemStack>,
    action: InventoryAction,
) {
    checkRows(row)
    checkColumnRange(columns)
    checkDelays(tickSpeed)
    checkItemCount(cycleItems)

    columns.forEach { column ->
        set(row, column, tickSpeed, cycleItems, action)
    }
}

/**
 * Sets the range of rows and columns provided to update and cycle every interval
 * specified with the items provided.
 * This item will be clickable.
 *
 * @param rows The rows to set.
 * @param columns The columns to set.
 * @param tickSpeed The interval to cycle the items.
 * @param cycleItems The items to cycle through.
 * @param action The action to perform when clicked.
 */
@NonPageSpecific
fun GuiContents.set(
    rows: IntRange,
    columns: IntRange,
    tickSpeed: Long,
    cycleItems: Collection<ItemStack>,
    action: InventoryAction,
) {
    checkRowRange(rows)
    checkColumnRange(columns)
    checkDelays(tickSpeed)
    checkItemCount(cycleItems)

    rows.forEach { row ->
        columns.forEach { column ->
            set(row, column, tickSpeed, cycleItems, action)
        }
    }
}