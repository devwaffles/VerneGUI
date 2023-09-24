package dev.butter.gui.internal.validation

import dev.butter.gui.api.base.GUIContents
import dev.butter.gui.internal.validation.RangeConstants.DEFAULT_COLUMNS
import dev.butter.gui.internal.validation.RangeConstants.DEFAULT_DELAYS

internal val GUIContents.slotRange
    get() = 0..<this.gui.inventory.size

internal val GUIContents.rowRange
    get() = 1..this.gui.inventory.size / 9

internal fun GUIContents.checkSlots(slot: Int) =
    require(slot in slotRange) {
        "Invalid slot ($slot) for GUI: ${gui::class.simpleName} - must be within $slotRange."
    }

internal fun GUIContents.checkSlotRange(slots: IntRange) =
    require(slots.first >= slotRange.first && slots.last <= slotRange.last) {
        "Invalid slot range ($slots) for GUI: ${gui::class.simpleName} - must be within $slotRange."
    }

internal fun GUIContents.checkRows(row: Int) =
    require(row in rowRange) {
        "Invalid row ($row) for GUI: ${gui::class.simpleName} - must be within $rowRange."
    }

internal fun GUIContents.checkRowRange(inputRange: IntRange) =
    require(inputRange.first >= rowRange.first && inputRange.last <= rowRange.last) {
        "Invalid row range ($inputRange) for GUI: ${gui::class.simpleName} - must be within $rowRange."
    }

internal fun GUIContents.checkColumns(column: Int) =
    require(column in DEFAULT_COLUMNS) {
        "Invalid column ($column) for GUI: ${this.gui::class.simpleName} - must be within $DEFAULT_COLUMNS."
    }

internal fun GUIContents.checkColumnRange(inputRange: IntRange) =
    require (inputRange.first >= DEFAULT_COLUMNS.first && inputRange.last <= DEFAULT_COLUMNS.last) {
        "Invalid column range ($inputRange) for GUI: ${gui::class.simpleName} - must be within $DEFAULT_COLUMNS."
    }

internal fun GUIContents.checkDelays(delay: Long) =
    require(delay in DEFAULT_DELAYS) {
        "Invalid delay ($delay) for GUI: ${gui::class.simpleName} - must be within $DEFAULT_DELAYS."
    }