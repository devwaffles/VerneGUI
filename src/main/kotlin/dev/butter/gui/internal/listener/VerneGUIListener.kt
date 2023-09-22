package dev.butter.gui.internal.listener

import dev.butter.gui.api.base.BaseGUI
import dev.butter.gui.internal.extensions.handle
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryDragEvent

internal object VerneGUIListener : Listener {
    @EventHandler
    fun on(event: InventoryDragEvent) {
        val inventory = event.inventory
        val holder = inventory.holder

        if (holder !is BaseGUI) {
            return
        }

        event.isCancelled = true
    }

    @EventHandler
    fun on(event: InventoryClickEvent) {
        val inventory = event.inventory
        val gui = inventory.holder as? BaseGUI ?: return
        val player = event.whoClicked as? Player ?: return
        val slot = event.rawSlot

        event.isCancelled = true

        gui.contents.handle(player, slot, event, gui)
    }
}