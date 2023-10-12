package dev.butter.gui.api.functions

import dev.butter.gui.api.base.GuiContents
import dev.butter.gui.api.item.types.DefaultPageInventoryAction
import dev.butter.gui.api.item.types.PageAction.REFRESH_PAGE
import dev.butter.gui.api.item.types.PageItem
import dev.butter.gui.internal.validation.*
import org.bukkit.inventory.ItemStack

/**
 * Sets a clickable item that will refresh the page when clicked.
 *
 * @param slot The slot to set.
 * @param item The item to set with.
 */
@NonPageSpecific
fun GuiContents.refresh(
    slot: Int,
    item: ItemStack,
) {
    checkSlots(slot)
    checkRefreshItem(slot)

    this.contentItems += slot to PageItem(item, REFRESH_PAGE, DefaultPageInventoryAction)
}

/**
 * Sets a clickable item that will refresh the page when clicked.
 *
 * @param row The row to set.
 * @param column The column to set.
 * @param item The item to set with.
 */
@NonPageSpecific
fun GuiContents.refresh(
    row: Int,
    column: Int,
    item: ItemStack,
) {
    checkRows(row)
    checkColumns(column)

    refresh(slotOf(row, column), item)
}
