package dev.butter.gui.internal.validation

import dev.butter.gui.api.base.GuiContents
import dev.butter.gui.api.item.types.GuiItem
import dev.butter.gui.api.item.types.PageAction.*
import dev.butter.gui.api.item.types.PageItem
import dev.butter.gui.api.type.GuiClass
import dev.butter.gui.internal.InternalGuiHandler.guis
import dev.butter.gui.internal.extensions.filterValuesIsInstance
import dev.butter.gui.internal.validation.GuiConstants.DEFAULT_COLUMNS
import dev.butter.gui.internal.validation.GuiConstants.DEFAULT_DELAYS
import org.bukkit.inventory.ItemStack

internal val GuiContents.slotRange
    get() = 0..<this.gui.inventory.size

internal val GuiContents.rowRange
    get() = 1..this.gui.inventory.size / 9

internal fun GuiContents.checkSlots(slot: Int) =
    require(slot in slotRange) {
        "Invalid slot ($slot) for GUI: ${gui::class.simpleName} - must be within $slotRange."
    }

internal fun GuiContents.checkSlots(slots: Collection<Int>) =
    slots.forEach(::checkSlots)

internal fun GuiContents.checkSlotRange(slots: IntRange) =
    require(slots.first >= slotRange.first && slots.last <= slotRange.last) {
        "Invalid slot range ($slots) for GUI: ${gui::class.simpleName} - must be within $slotRange."
    }

internal fun GuiContents.checkRows(row: Int) =
    require(row in rowRange) {
        "Invalid row ($row) for GUI: ${gui::class.simpleName} - must be within $rowRange."
    }

internal fun GuiContents.checkRows(rows: Collection<Int>) =
    rows.forEach(::checkRows)

internal fun GuiContents.checkRowRange(inputRange: IntRange) =
    require(inputRange.first >= rowRange.first && inputRange.last <= rowRange.last) {
        "Invalid row range ($inputRange) for GUI: ${gui::class.simpleName} - must be within $rowRange."
    }

internal fun GuiContents.checkColumns(column: Int) =
    require(column in DEFAULT_COLUMNS) {
        "Invalid column ($column) for GUI: ${this.gui::class.simpleName} - must be within $DEFAULT_COLUMNS."
    }

internal fun GuiContents.checkColumns(columns: Collection<Int>) =
    columns.forEach(::checkColumns)

internal fun GuiContents.checkColumnRange(inputRange: IntRange) =
    require(inputRange.first >= DEFAULT_COLUMNS.first && inputRange.last <= DEFAULT_COLUMNS.last) {
        "Invalid column range ($inputRange) for GUI: ${gui::class.simpleName} - must be within $DEFAULT_COLUMNS."
    }

internal fun GuiContents.checkDelays(delay: Long) =
    require(delay in DEFAULT_DELAYS) {
        "Invalid delay ($delay) for GUI: ${gui::class.simpleName} - must be within $DEFAULT_DELAYS."
    }

internal fun GuiContents.checkItemCount(items: Collection<ItemStack>) =
    require(items.size <= 1) {
        "Must have more than one item to cycle through for GUI: ${gui::class.simpleName}."
    }

internal fun GuiContents.checkLinkingGui(linkingGui: GuiClass) {
    val currentGui = gui::class

    require(currentGui != linkingGui) {
        "Cannot link GUI: ${gui::class.simpleName} to itself."
    }

    require(linkingGui in guis) {
        "Cannot link GUI: ${gui::class.simpleName} to an unregistered GUI: ${linkingGui.simpleName}."
    }
}

internal fun GuiContents.checkNextPageItem(slot: Int) {
    val duplicateNextPage = this.contentItems
        .filterValuesIsInstance<Int, GuiItem, PageItem>()
        .filterValues { it.pageAction == NEXT_PAGE }
        .entries
        .firstOrNull() ?: return

    require(slot == duplicateNextPage.key) {
        "Cannot have more than one next page scroll item for GUI: ${gui::class.simpleName}."
    }
}

internal fun GuiContents.checkPreviousPageItem(slot: Int) {
    val duplicatePreviousPage = this.contentItems
        .filterValuesIsInstance<Int, GuiItem, PageItem>()
        .filterValues { it.pageAction == PREVIOUS_PAGE }
        .entries
        .firstOrNull() ?: return

    require(slot == duplicatePreviousPage.key) {
        "Cannot have more than one previous page scroll item for GUI: ${gui::class.simpleName}."
    }
}

internal fun GuiContents.checkSeekFirstItem(slot: Int) {
    val duplicateSeekFirst = this.contentItems
        .filterValuesIsInstance<Int, GuiItem, PageItem>()
        .filterValues { it.pageAction == FIRST_PAGE }
        .entries
        .firstOrNull() ?: return

    require(slot == duplicateSeekFirst.key) {
        "Cannot have more than one seek first page scroll item for GUI: ${gui::class.simpleName}."
    }
}

internal fun GuiContents.checkSeekLastItem(slot: Int) {
    val duplicateSeekLast = this.contentItems
        .filterValuesIsInstance<Int, GuiItem, PageItem>()
        .filterValues { it.pageAction == LAST_PAGE }
        .entries
        .firstOrNull() ?: return

    require(slot == duplicateSeekLast.key) {
        "Cannot have more than one seek last page scroll item for GUI: ${gui::class.simpleName}."
    }
}

internal fun GuiContents.checkSeekMiddleItem(slot: Int) {
    val duplicateSeekMiddle = this.contentItems
        .filterValuesIsInstance<Int, GuiItem, PageItem>()
        .filterValues { it.pageAction == MIDDLE_PAGE }
        .entries
        .firstOrNull() ?: return

    require(slot == duplicateSeekMiddle.key) {
        "Cannot have more than one seek middle page scroll item for GUI: ${gui::class.simpleName}."
    }
}

internal fun GuiContents.checkRefreshItem(slot: Int) {
    val duplicateRefresh = this.contentItems
        .filterValuesIsInstance<Int, GuiItem, PageItem>()
        .filterValues { it.pageAction == REFRESH_PAGE }
        .entries
        .firstOrNull() ?: return

    require(slot == duplicateRefresh.key) {
        "Cannot have more than one refresh page item for GUI: ${gui::class.simpleName}."
    }
}