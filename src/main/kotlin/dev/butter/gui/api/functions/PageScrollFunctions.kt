package dev.butter.gui.api.functions

import dev.butter.gui.api.base.GuiContents
import dev.butter.gui.api.item.types.PageAction.*
import dev.butter.gui.api.item.types.PageItem
import dev.butter.gui.internal.validation.*
import org.bukkit.inventory.ItemStack

/**
 * Sets a clickable item that will go to the next page when clicked.
 *
 * @param slot The slot to set.
 * @param item The item to set with.
 */
@NonPageSpecific
fun GuiContents.next(
    slot: Int,
    item: ItemStack,
) {
    checkNextPageItem(slot)

    this.contentItems += slot to PageItem(item, NEXT_PAGE)
}

/**
 * Sets a clickable item that will go to the next page when clicked.
 *
 * @param row The row to set.
 * @param column The column to set.
 * @param item The item to set with.
 */
@NonPageSpecific
fun GuiContents.next(
    row: Int,
    column: Int,
    item: ItemStack,
) {
    checkRows(row)
    checkColumns(column)

    next(slotOf(row, column), item)
}

/**
 * Sets a clickable item that will go to the previous page when clicked.
 *
 * @param slot The slot to set.
 * @param item The item to set with.
 */
@NonPageSpecific
fun GuiContents.previous(
    slot: Int,
    item: ItemStack,
) {
    checkSlots(slot)
    checkPreviousPageItem(slot)

    this.contentItems += slot to PageItem(item, PREVIOUS_PAGE)
}

/**
 * Sets a clickable item that will go to the previous page when clicked.
 *
 * @param row The row to set.
 * @param column The column to set.
 */
@NonPageSpecific
fun GuiContents.previous(
    row: Int,
    column: Int,
    item: ItemStack,
) {
    checkRows(row)
    checkColumns(column)

    previous(slotOf(row, column), item)
}

/**
 * Sets a clickable item that will go to the first page when clicked.
 *
 * @param slot The slot to set.
 * @param item The item to set with.
 */
@NonPageSpecific
fun GuiContents.seekFirst(
    slot: Int,
    item: ItemStack,
) {
    checkSlots(slot)
    checkSeekFirstItem(slot)

    this.contentItems += slot to PageItem(item, FIRST_PAGE)
}

/**
 * Sets a clickable item that will go to the first page when clicked.
 *
 * @param row The row to set.
 * @param column The column to set.
 * @param item The item to set with.
 */
@NonPageSpecific
fun GuiContents.seekFirst(
    row: Int,
    column: Int,
    item: ItemStack,
) {
    checkRows(row)
    checkColumns(column)

    seekFirst(slotOf(row, column), item)
}

/**
 * Sets a clickable item that will go to the last page when clicked.
 *
 * @param slot The slot to set.
 * @param item The item to set with.
 */
@NonPageSpecific
fun GuiContents.seekLast(
    slot: Int,
    item: ItemStack,
) {
    checkSlots(slot)
    checkSeekLastItem(slot)

    this.contentItems += slot to PageItem(item, LAST_PAGE)
}

/**
 * Sets a clickable item that will go to the last page when clicked.
 *
 * @param row The row to set.
 * @param column The column to set.
 * @param item The item to set with.
 */
@NonPageSpecific
fun GuiContents.seekLast(
    row: Int,
    column: Int,
    item: ItemStack,
) {
    checkRows(row)
    checkColumns(column)

    seekLast(slotOf(row, column), item)
}

/**
 * Sets a clickable item that will go to the middle page when clicked.
 *
 * @param slot The slot to set.
 * @param item The item to set with.
 */
@NonPageSpecific
fun GuiContents.seekMiddle(
    slot: Int,
    item: ItemStack,
) {
    checkSlots(slot)
    checkSeekMiddleItem(slot)

    this.contentItems += slot to PageItem(item, MIDDLE_PAGE)
}

/**
 * Sets a clickable item that will go to the middle page when clicked.
 *
 * @param row The row to set.
 * @param column The column to set.
 * @param item The item to set with.
 */
@NonPageSpecific
fun GuiContents.seekMiddle(
    row: Int,
    column: Int,
    item: ItemStack,
) {
    checkRows(row)
    checkColumns(column)

    seekMiddle(slotOf(row, column), item)
}