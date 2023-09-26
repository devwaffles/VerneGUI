package dev.butter.gui.api.base

import dev.butter.gui.api.item.types.*
import dev.butter.gui.internal.validation.*
import org.bukkit.inventory.ItemStack

/**
 * Determines the slot of a given row and column.
 *
 * @param row The row to get the slot of.
 * @param column The column to get the slot of.
 */
fun slotOf(row: Int, column: Int) =
    (row - 1) * 9 + (column - 1)

/**
 * Sets the slot provided to the given item and action.
 * This item will be clickable.
 *
 * @param slot The slot to update.
 * @param item The item to set with.
 * @param action The action to perform when clicked.
 */
@NonPageSpecific
fun GUIContents.set(
    slot: Int,
    item: ItemStack,
    action: InventoryAction,
) {
    checkSlots(slot)

    this.constantItems += slot to ClickableItem(item, action)
}

/**
 * Sets the row and column provided to the given item and action.
 * This item will be clickable.
 *
 * @param row The row to update.
 * @param column The column to update.
 * @param item The item to set with.
 * @param action The action to perform when clicked.
 */
@NonPageSpecific
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

/**
 * Sets the slot provided to update and cycle every interval
 * specified with the items provided.
 *
 * @param slot The slot to update.
 * @param tickSpeed The interval to cycle the items.
 * @param cycleItems The items to cycle through.
 */
@NonPageSpecific
fun GUIContents.set(
    slot: Int,
    tickSpeed: Long,
    vararg cycleItems: ItemStack
) {
    checkSlots(slot)
    checkDelays(tickSpeed)

    this.constantItems += slot to AnimatedItem(tickSpeed, cycleItems.toList(), checkAnimated(slot) ?: cycleItems.first())
}

/**
 * Sets the row and column provided to update and cycle every interval
 * specified with the items provided.
 *
 * @param row The row to update.
 * @param column The column to update.
 * @param tickSpeed The interval to cycle the items.
 * @param cycleItems The items to cycle through.
 */
@NonPageSpecific
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

/**
 * Sets the range of rows and column provided to update and cycle every interval
 * specified with the items provided.
 *
 * @param rows The rows to update.
 * @param column The column to update.
 * @param tickSpeed The interval to cycle the items.
 * @param cycleItems The items to cycle through.
 */
@NonPageSpecific
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

/**
 * Sets the row and range of columns provided to update and cycle every interval
 * specified with the items provided.
 *
 * @param row The row to update.
 * @param columns The columns to update.
 * @param tickSpeed The interval to cycle the items.
 * @param cycleItems The items to cycle through.
 */
@NonPageSpecific
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

/**
 * Sets the slot provided to update and cycle every interval
 * specified with the items provided.
 * This item will be clickable.
 *
 * @param slot The slot to update.
 * @param tickSpeed The interval to cycle the items.
 * @param cycleItems The items to cycle through.
 * @param action The action to perform when clicked.
 */
@NonPageSpecific
fun GUIContents.set(
    slot: Int,
    tickSpeed: Long,
    cycleItems: List<ItemStack>,
    action: InventoryAction,
) {
    checkSlots(slot)
    checkDelays(tickSpeed)

    this.constantItems += slot to AnimatedClickableItem(tickSpeed, cycleItems, action, checkAnimated(slot) ?: cycleItems.first())
}

/**
 * Sets the row and column provided to update and cycle every interval
 * specified with the items provided.
 * This item will be clickable.
 *
 * @param row The row to update.
 * @param column The column to update.
 * @param tickSpeed The interval to cycle the items.
 * @param cycleItems The items to cycle through.
 * @param action The action to perform when clicked.
 */
@NonPageSpecific
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

/**
 * Sets the range of rows and column provided to update and cycle every interval
 * specified with the items provided.
 * This item will be clickable.
 *
 * @param rows The rows to update.
 * @param column The column to update.
 * @param tickSpeed The interval to cycle the items.
 * @param cycleItems The items to cycle through.
 * @param action The action to perform when clicked.
 */
@NonPageSpecific
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

/**
 * Sets the row and range of columns provided to update and cycle every interval
 * specified with the items provided.
 * This item will be clickable.
 *
 * @param row The row to update.
 * @param columns The columns to update.
 * @param tickSpeed The interval to cycle the items.
 * @param cycleItems The items to cycle through.
 * @param action The action to perform when clicked.
 */
@NonPageSpecific
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

/**
 * Sets the slot provided to the given item and closes
 * the inventory when clicked.
 *
 * @param slot The slot to update.
 * @param item The item to set with.
 */
@NonPageSpecific
fun GUIContents.close(
    slot: Int,
    item: ItemStack,
) {
    checkSlots(slot)

    this.constantItems += slot to ClickableItem(item) { player, _ -> player.closeInventory() }
}

/**
 * Sets the row and column provided to the given item and closes
 * the inventory when clicked.
 *
 * @param row The row to update.
 * @param column The column to update.
 * @param item The item to set with.
 */
@NonPageSpecific
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
    val duplicate = this.constantItems[slot] ?: return null
    val animated = duplicate as? Animated ?: return null

    if (duplicate.item !in animated.cycleItems) {
        return null
    }

    return duplicate.item
}