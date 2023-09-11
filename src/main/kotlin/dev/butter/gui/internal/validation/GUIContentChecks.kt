package dev.butter.gui.internal.validation

import dev.butter.gui.api.base.GUIContents
import dev.butter.gui.internal.exception.contents.ContentColumnRangeException
import dev.butter.gui.internal.exception.contents.ContentRowRangeException
import dev.butter.gui.internal.exception.contents.ContentSlotRangeException
import dev.butter.gui.internal.exception.input.InvalidColumnException
import dev.butter.gui.internal.exception.input.InvalidDelaysException
import dev.butter.gui.internal.exception.input.InvalidRowException
import dev.butter.gui.internal.exception.input.InvalidSlotException
import dev.butter.gui.internal.validation.RangeConstants.DEFAULT_DELAYS

internal val GUIContents.slotRange
    get() = 0..<this.inventory.size

internal fun GUIContents.checkSlots(slot: Int): Boolean {
    if (slot !in this.slotRange) {
        throw InvalidSlotException(this.guiClass, slot)
    }

    return true
}

internal fun GUIContents.checkSlotRange(slots: IntRange): Boolean {
    if (slots.first < this.slotRange.first || slots.last > this.slotRange.last) {
        throw ContentSlotRangeException(this, slots)
    }

    return true
}

internal fun GUIContents.checkRowRange(inputRange: IntRange): Boolean {
    if (inputRange.first < 1 || inputRange.last > 6) {
        throw ContentRowRangeException(this, inputRange)
    }

    return true
}

internal fun GUIContents.checkColumnRange(inputRange: IntRange): Boolean {
    if (inputRange.first < 1 || inputRange.last > 9) {
        throw ContentColumnRangeException(this, inputRange)
    }

    return true
}

internal fun GUIContents.checkRows(row: Int): Boolean {
    if (row !in 1..6) {
        throw InvalidRowException(this.guiClass, row)
    }

    return true
}

internal fun GUIContents.checkColumns(column: Int): Boolean {
    if (column !in 1..9) {
        throw InvalidColumnException(this.guiClass, column)
    }

    return true
}

internal fun GUIContents.checkDelays(delay: Long): Boolean {
    if (delay !in DEFAULT_DELAYS) {
        throw InvalidDelaysException(this.guiClass, delay)
    }

    return true
}