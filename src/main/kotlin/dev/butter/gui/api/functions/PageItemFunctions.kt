package dev.butter.gui.api.functions

import dev.butter.gui.api.base.GuiContents
import dev.butter.gui.internal.validation.PageSpecific
import org.bukkit.inventory.ItemStack

/**
 * Adds items to the page items.
 *
 * @param items The items to add.
 */
@PageSpecific
fun GuiContents.add(vararg items: ItemStack) {
    pageItems += items
}

/**
 * Adds items to the page items.
 *
 * @param items The items to add.
 */
@PageSpecific
fun GuiContents.add(items: Collection<ItemStack>) {
    pageItems += items
}

/**
 * Removes items from the page items.
 *
 * @param items The items to remove.
 */
@PageSpecific
fun GuiContents.remove(vararg items: ItemStack) {
    pageItems -= items.toSet()
}

/**
 * Removes items from the page items.
 *
 * @param items The items to remove.
 */
@PageSpecific
fun GuiContents.remove(items: Collection<ItemStack>) {
    pageItems -= items.toSet()
}

/**
 * Removes an amount of items from the start of the page items.
 *
 * @param amount The amount of items to remove.
 */
@PageSpecific
fun GuiContents.removeStart(amount: Int) {
    if (amount >= pageItems.size) {
        return clearPageItems()
    }

    pageItems.retainAll(pageItems.drop(amount))
}

/**
 * Removes an amount of items from the end of the page items.
 *
 * @param amount The amount of items to remove.
 */
@PageSpecific
fun GuiContents.removeEnd(amount: Int) {
    if (amount >= pageItems.size) {
        return clearPageItems()
    }

    pageItems.retainAll(pageItems.dropLast(amount))
}

/**
 * Clears the page items.
 */
@PageSpecific
fun GuiContents.clearPageItems() = pageItems.clear()