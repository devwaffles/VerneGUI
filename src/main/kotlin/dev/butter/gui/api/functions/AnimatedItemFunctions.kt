package dev.butter.gui.api.functions

import dev.butter.gui.api.base.GuiContents
import dev.butter.gui.api.item.types.Animated
import dev.butter.gui.api.item.types.AnimatedItem
import dev.butter.gui.internal.validation.*
import org.bukkit.inventory.ItemStack

/**
 * Sets the slot provided to update and cycle every interval
 * specified with the items provided.
 *
 * @param slot The slot to set.
 * @param tickSpeed The interval to cycle the items.
 * @param cycleItems The items to cycle through.
 */
@NonPageSpecific
fun GuiContents.set(
    slot: Int,
    tickSpeed: Long,
    vararg cycleItems: ItemStack
) {
    checkSlots(slot)
    checkDelays(tickSpeed)
    checkItemCount(cycleItems.toList())

    this.contentItems += slot to
        AnimatedItem(
            tickSpeed,
            cycleItems.toList(),
            checkAnimated(slot) ?: cycleItems.first(),
        )
}


/**
 * Sets the slot provided to update and cycle every interval
 * specified with the items provided.
 *
 * @param slot The slot to set.
 * @param tickSpeed The interval to cycle the items.
 * @param cycleItems The items to cycle through.
 */
@NonPageSpecific
fun GuiContents.set(
    slot: Int,
    tickSpeed: Long,
    cycleItems: Collection<ItemStack>
) {
    checkSlots(slot)
    checkDelays(tickSpeed)
    checkItemCount(cycleItems)

    this.contentItems += slot to
            AnimatedItem(
                tickSpeed,
                cycleItems.toList(),
                checkAnimated(slot) ?: cycleItems.first(),
            )
}

/**
 * Sets the row and column provided to update and cycle every interval
 * specified with the items provided.
 *
 * @param row The row to set.
 * @param column The column to set.
 * @param tickSpeed The interval to cycle the items.
 * @param cycleItems The items to cycle through.
 */
@NonPageSpecific
fun GuiContents.set(
    row: Int,
    column: Int,
    tickSpeed: Long,
    vararg cycleItems: ItemStack
) {
    checkRows(row)
    checkColumns(column)
    checkDelays(tickSpeed)
    checkItemCount(cycleItems.toList())

    val slot = slotOf(row, column)

    this.contentItems += slot to
            AnimatedItem(
                tickSpeed,
                cycleItems.toList(),
                checkAnimated(slot) ?: cycleItems.first(),
            )
}

/**
 * Sets the row and column provided to update and cycle every interval
 * specified with the items provided.
 *
 * @param row The row to set.
 * @param column The column to set.
 * @param tickSpeed The interval to cycle the items.
 * @param cycleItems The items to cycle through.
 */
@NonPageSpecific
fun GuiContents.set(
    row: Int,
    column: Int,
    tickSpeed: Long,
    cycleItems: Collection<ItemStack>
) {
    checkRows(row)
    checkColumns(column)
    checkDelays(tickSpeed)
    checkItemCount(cycleItems)

    val slot = slotOf(row, column)

    this.contentItems += slot to
            AnimatedItem(
                tickSpeed,
                cycleItems.toList(),
                checkAnimated(slot) ?: cycleItems.first(),
            )
}

/**
 * Sets the range of rows and column provided to update and cycle every interval
 * specified with the items provided.
 *
 * @param rows The rows to set.
 * @param column The column to set.
 * @param tickSpeed The interval to cycle the items.
 * @param cycleItems The items to cycle through.
 */
@NonPageSpecific
fun GuiContents.set(
    rows: IntRange,
    column: Int,
    tickSpeed: Long,
    vararg cycleItems: ItemStack
) {
    checkRowRange(rows)
    checkColumns(column)
    checkDelays(tickSpeed)
    checkItemCount(cycleItems.toList())

    rows.forEach { row ->
        set(row, column, tickSpeed, *cycleItems)
    }
}

/**
 * Sets the range of rows and column provided to update and cycle every interval
 * specified with the items provided.
 *
 * @param rows The rows to set.
 * @param column The column to set.
 * @param tickSpeed The interval to cycle the items.
 * @param cycleItems The items to cycle through.
 */
@NonPageSpecific
fun GuiContents.set(
    rows: IntRange,
    column: Int,
    tickSpeed: Long,
    cycleItems: Collection<ItemStack>
) {
    checkRowRange(rows)
    checkColumns(column)
    checkDelays(tickSpeed)
    checkItemCount(cycleItems)

    rows.forEach { row ->
        set(row, column, tickSpeed, cycleItems)
    }
}

/**
 * Sets the row and range of columns provided to update and cycle every interval
 * specified with the items provided.
 *
 * @param row The row to set.
 * @param columns The columns to set.
 * @param tickSpeed The interval to cycle the items.
 * @param cycleItems The items to cycle through.
 */
@NonPageSpecific
fun GuiContents.set(
    row: Int,
    columns: IntRange,
    tickSpeed: Long,
    vararg cycleItems: ItemStack
) {
    checkRows(row)
    checkColumnRange(columns)
    checkDelays(tickSpeed)
    checkItemCount(cycleItems.toList())

    columns.forEach { column ->
        set(row, column, tickSpeed, *cycleItems)
    }
}

/**
 * Sets the row and range of columns provided to update and cycle every interval
 * specified with the items provided.
 *
 * @param row The row to set.
 * @param columns The columns to set.
 * @param tickSpeed The interval to cycle the items.
 * @param cycleItems The items to cycle through.
 */
@NonPageSpecific
fun GuiContents.set(
    row: Int,
    columns: IntRange,
    tickSpeed: Long,
    cycleItems: Collection<ItemStack>
) {
    checkRows(row)
    checkColumnRange(columns)
    checkDelays(tickSpeed)
    checkItemCount(cycleItems)

    columns.forEach { column ->
        set(row, column, tickSpeed, cycleItems)
    }
}

/**
 * Sets the range of rows and range of columns provided to update and cycle every interval
 * specified with the items provided.
 *
 * @param rows The rows to set.
 * @param columns The columns to set.
 * @param tickSpeed The interval to cycle the items.
 * @param cycleItems The items to cycle through.
 */
@NonPageSpecific
fun GuiContents.set(
    rows: IntRange,
    columns: IntRange,
    tickSpeed: Long,
    vararg cycleItems: ItemStack
) {
    checkRowRange(rows)
    checkColumnRange(columns)
    checkDelays(tickSpeed)
    checkItemCount(cycleItems.toList())

    rows.forEach { row ->
        columns.forEach { column ->
            set(row, column, tickSpeed, *cycleItems)
        }
    }
}

/**
 * Sets the range of rows and range of columns provided to update and cycle every interval
 * specified with the items provided.
 *
 * @param rows The rows to set.
 * @param columns The columns to set.
 * @param tickSpeed The interval to cycle the items.
 * @param cycleItems The items to cycle through.
 */
@NonPageSpecific
fun GuiContents.set(
    rows: IntRange,
    columns: IntRange,
    tickSpeed: Long,
    cycleItems: Collection<ItemStack>
) {
    checkRowRange(rows)
    checkColumnRange(columns)
    checkDelays(tickSpeed)
    checkItemCount(cycleItems)

    rows.forEach { row ->
        columns.forEach { column ->
            set(row, column, tickSpeed, cycleItems)
        }
    }
}

internal fun GuiContents.checkAnimated(slot: Int): ItemStack? {
    val duplicate = this.contentItems[slot] ?: return null
    val animated = duplicate as? Animated ?: return null

    if (duplicate.stack !in animated.cycleItems) {
        return null
    }

    return duplicate.stack
}