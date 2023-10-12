package dev.butter.gui.api.functions

/**
 * Returns the inventory slot of the given row and column.
 *
 * @param row The row of the slot.
 * @param column The column of the slot.
 */
fun slotOf(row: Int, column: Int): Int =
    (row - 1) * 9 + (column - 1)

/**
 * Returns the inventory slots of the given rows and column.
 *
 * @param rows The rows of the slots.
 * @param column The column of the slots.
 */
fun slotsOf(rows: IntRange, column: Int): List<Int> =
    rows.map { row ->
        slotOf(row, column)
    }

/**
 * Returns the inventory slots of the given row and columns.
 *
 * @param row The row of the slots.
 * @param columns The columns of the slots.
 */
fun slotsOf(row: Int, columns: IntRange): List<Int> =
    columns.map { column ->
        slotOf(row, column)
    }

/**
 * Returns the inventory slots of the given rows and columns.
 *
 * @param rows The rows of the slots.
 * @param columns The columns of the slots.
 */
fun slotsOf(rows: IntRange, columns: IntRange): List<Int> =
    rows.flatMap { row ->
        columns.map { column ->
            slotOf(row, column)
        }
    }