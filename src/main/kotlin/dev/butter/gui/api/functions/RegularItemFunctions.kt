package dev.butter.gui.api.functions

import dev.butter.gui.api.base.GuiContents
import dev.butter.gui.api.extensions.innerSlots
import dev.butter.gui.api.extensions.outerSlots
import dev.butter.gui.api.item.types.ClickableItem
import dev.butter.gui.api.item.types.DefaultPlayerAction
import dev.butter.gui.api.item.types.PlayerAction
import dev.butter.gui.api.item.types.RegularItem
import dev.butter.gui.internal.validation.*
import org.bukkit.inventory.ItemStack

/**
 * Sets the slot provided to the given item.
 *
 * @param slot The slot to set.
 * @param item The item to set with.
 */
@NonPageSpecific
fun GuiContents.set(
    slot: Int,
    item: ItemStack,
) {
    checkSlots(slot)

    this.contentItems += slot to RegularItem(item)
}

/**
 * Sets the row and column provided to the given item.
 *
 * @param row The row to set.
 * @param column The column to set.
 * @param item The item to set with.
 */
@NonPageSpecific
fun GuiContents.set(
    row: Int,
    column: Int,
    item: ItemStack,
) {
    checkRows(row)
    checkColumns(column)

    set(slotOf(row, column), item)
}

/**
 * Sets the range of rows and column provided to the given item.
 *
 * @param rows The rows to set.
 * @param column The column to set.
 * @param item The item to set with.
 */
@NonPageSpecific
fun GuiContents.set(
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
 * @param row The row to set.
 * @param columns The columns to set.
 * @param item The item to set with.
 */
@NonPageSpecific
fun GuiContents.set(
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
 * Sets the range of rows and columns provided to the given item.
 *
 * @param rows The rows to set.
 * @param columns The columns to set.
 * @param item The item to set with.
 */
@NonPageSpecific
fun GuiContents.set(
    rows: IntRange,
    columns: IntRange,
    item: ItemStack,
) {
    checkRowRange(rows)
    checkColumnRange(columns)

    rows.forEach { row ->
        columns.forEach { column ->
            set(row, column, item)
        }
    }
}

/**
 * Sets the range of slots provided to the given item.
 *
 * @param slots The slots to set.
 * @param item The item to set with.
 */
@NonPageSpecific
fun GuiContents.set(
    slots: IntRange,
    item: ItemStack,
) {
    checkSlotRange(slots)

    slots.forEach { slot ->
        set(slot, item)
    }
}

/**
 * Fills the entire GUI with a given item.
 *
 * @param item The item to fill the GUI with.
 */
@NonPageSpecific
fun GuiContents.fill(item: ItemStack) = this.slotRange
    .filterNot(this.contentItems.keys::contains)
    .forEach { slot -> this.set(slot, item) }

/**
 * Fills the outer edges of the GUI with a given item.
 *
 * @param item The item to fill the outer edges with.
 */
@NonPageSpecific
fun GuiContents.fillOuter(item: ItemStack) =
    this.gui.outerSlots.forEach { slot ->
        this.set(slot, item)
    }

/**
 * Fills the inner part of the GUI with a given item.
 *
 * @param item The item to fill the inner part with.
 */
@NonPageSpecific
fun GuiContents.fillInner(item: ItemStack) =
    this.gui.innerSlots.forEach { slot ->
        this.set(slot, item)
    }

/**
 * Sets the slot provided to the given item and closes
 * the inventory when clicked.
 *
 * @param slot The slot to set.
 * @param item The item to set with.
 * @param action The action to perform when clicked.
 */
@NonPageSpecific
fun GuiContents.close(
    slot: Int,
    item: ItemStack,
    action: PlayerAction = DefaultPlayerAction,
) {
    checkSlots(slot)

    this.contentItems += slot to ClickableItem(item) { player, _ ->
        action(player)
        player.closeInventory()
    }
}

/**
 * Sets the row and column provided to the given item and closes
 * the inventory when clicked.
 *
 * @param row The row to set.
 * @param column The column to set.
 * @param item The item to set with.
 * @param action The action to perform when clicked.
 */
@NonPageSpecific
fun GuiContents.close(
    row: Int,
    column: Int,
    item: ItemStack,
    action: PlayerAction = DefaultPlayerAction,
) {
    checkRows(row)
    checkColumns(column)

    close(slotOf(row, column), item, action)
}