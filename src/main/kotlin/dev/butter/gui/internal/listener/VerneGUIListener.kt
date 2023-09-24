package dev.butter.gui.internal.listener

import dev.butter.gui.api.base.BaseGUI
import dev.butter.gui.internal.InternalGUIHandler.guiDelays
import dev.butter.gui.internal.extensions.handle
import dev.butter.gui.internal.updater.GUIUpdater.currentTick
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent
import java.util.*

internal object VerneGUIListener : Listener {
    private val playerClickTimes = mutableMapOf<UUID, Long>()

    @EventHandler(ignoreCancelled = true)
    fun on(event: InventoryDragEvent) {
        val inventory = event.inventory
        val holder = inventory.holder

        if (holder !is BaseGUI) {
            return
        }

        event.isCancelled = true
    }

    @EventHandler(ignoreCancelled = true)
    fun on(event: InventoryClickEvent) {
        val inventory = event.inventory
        val gui = inventory.holder as? BaseGUI ?: return
        val player = event.whoClicked as? Player ?: return

        val slot = event.rawSlot
        val uuid = player.uniqueId
        val lastClickTime = playerClickTimes[uuid] ?: currentTick
        val clickDelay = guiDelays[gui::class]!!

        event.isCancelled = true

        if (uuid in playerClickTimes.keys && currentTick - lastClickTime < clickDelay) {
            return
        }

        playerClickTimes[uuid] = currentTick

        gui.contents.handle(player, slot, event, gui)
    }

    @EventHandler(ignoreCancelled = true)
    fun on(event: InventoryCloseEvent) {
        val inventory = event.inventory
        val holder = inventory.holder
        val player = event.player

        if (holder !is BaseGUI) {
            return
        }

        playerClickTimes.remove(player.uniqueId)
    }
}