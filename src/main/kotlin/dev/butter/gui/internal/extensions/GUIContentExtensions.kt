package dev.butter.gui.internal.extensions

import dev.butter.gui.api.base.GUIContents
import dev.butter.gui.api.base.VerneBaseGUI
import dev.butter.gui.api.item.Clickable
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent

internal fun GUIContents.init() =
    items.forEach { guiItem ->
        this.gui.inventory.setItem(guiItem.slot, guiItem.item)
    }

internal fun GUIContents.clear() {
    this.gui.inventory.clear()
    this.items.clear()
}

internal fun GUIContents.handle(
    player: Player,
    slot: Int,
    event: InventoryClickEvent,
    gui: VerneBaseGUI,
): Boolean {
    val item = items.find { it.slot == slot } ?: return false
    val clickable = item as? Clickable ?: return false

    clickable.action(player, event.click)

    gui.update()

    return true
}