package dev.butter.gui.internal.listener

import dev.butter.gui.api.base.VerneBaseGUI
import dev.butter.gui.internal.extensions.handle
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryDragEvent

internal object VerneGUIListener : Listener {
    @EventHandler
    fun on(event: InventoryDragEvent) {
        if (event.inventory !is VerneBaseGUI) {
            return
        }

        event.isCancelled = true
    }

    @EventHandler
    fun on(event: InventoryClickEvent) {
        val gui = event.inventory as? VerneBaseGUI ?: return
        val player = event.whoClicked as? Player ?: return
        val slot = event.rawSlot

        gui.contents.handle(player, slot, event, gui)
    }
}