package dev.butter.gui.internal.extensions

import dev.butter.gui.api.base.BaseGUI
import dev.butter.gui.api.base.GUIContents
import dev.butter.gui.api.base.update
import dev.butter.gui.api.item.types.Animated
import dev.butter.gui.api.item.types.Clickable
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent

internal fun GUIContents.init() =
    items.forEach { (slot, guiItem) ->
        this.gui.inventory.setItem(slot, guiItem.item)
    }

internal fun GUIContents.handle(
    player: Player,
    slot: Int,
    event: InventoryClickEvent,
    gui: BaseGUI,
) {
    val item = items[slot] ?: return
    val clickable = item as? Clickable ?: return

    clickable.action(player, event.click)

    gui.update()
}

internal fun GUIContents.hasAnimatedItems() = this.items.values.anyInstanceOf<Animated>()