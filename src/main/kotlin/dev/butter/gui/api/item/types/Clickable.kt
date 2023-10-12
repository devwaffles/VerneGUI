package dev.butter.gui.api.item.types

/**
 * Represents a clickable item type.
 */
internal sealed interface Clickable {
    val action: InventoryAction
}