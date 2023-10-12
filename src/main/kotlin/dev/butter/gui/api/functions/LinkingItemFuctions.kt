package dev.butter.gui.api.functions

import dev.butter.gui.api.base.BaseGui
import dev.butter.gui.api.base.GuiContents
import dev.butter.gui.api.item.types.DefaultPlayerAction
import dev.butter.gui.api.item.types.LinkingItem
import dev.butter.gui.api.item.types.PlayerAction
import dev.butter.gui.api.type.GuiClass
import dev.butter.gui.internal.validation.*
import org.bukkit.inventory.ItemStack

/**
 * Links a [BaseGui] to a clickable slot, sending
 * the player to the specified GUI.
 *
 * @param slot The slot to link the [BaseGui] to.
 * @param item The item to set with.
 * @param linkingGui The [BaseGui] to link to the slot.
 * @param action The action to perform when the item is clicked. (optional)
 */
@NonPageSpecific
fun GuiContents.link(
    slot: Int,
    item: ItemStack,
    linkingGui: GuiClass,
    action: PlayerAction = DefaultPlayerAction,
) {
    checkSlots(slot)
    checkLinkingGui(linkingGui)

    this.contentItems += slot to LinkingItem(item, linkingGui, action)
}


/**
 * Links a [BaseGui] to a clickable slot, sending
 * the player to the specified GUI.
 *
 * @param row The row to link the [BaseGui] to.
 * @param column The column to link the [BaseGui] to.
 * @param item The item to set with.
 * @param linkingGui The [BaseGui] to link to the slot.
 * @param action The action to perform when the item is clicked. (optional)
 */
@NonPageSpecific
fun GuiContents.link(
    row: Int,
    column: Int,
    item: ItemStack,
    linkingGui: GuiClass,
    action: PlayerAction = DefaultPlayerAction,
) {
    checkRows(row)
    checkColumns(column)
    checkLinkingGui(linkingGui)

    link(slotOf(row, column), item, linkingGui, action)
}