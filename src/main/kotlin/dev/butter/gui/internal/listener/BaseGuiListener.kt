package dev.butter.gui.internal.listener

import dev.butter.gui.api.base.BaseGui
import dev.butter.gui.internal.extensions.clickDelay
import dev.butter.gui.internal.extensions.handle
import dev.butter.gui.internal.extensions.update
import dev.butter.gui.internal.update.AnimatedItemRunnable.currentTick
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import java.util.*

internal object BaseGuiListener : Listener {
    private val playerClickTimes = mutableMapOf<UUID, Long>()

    @EventHandler(ignoreCancelled = true)
    fun on(event: InventoryDragEvent) {
        val inventory = event.inventory
        val holder = inventory.holder

        if (holder !is BaseGui) {
            return
        }

        event.isCancelled = true
    }

    @EventHandler(ignoreCancelled = true)
    fun on(event: InventoryClickEvent) {
        val inventory = event.inventory
        val gui = inventory.holder as? BaseGui ?: return
        val player = event.whoClicked as? Player ?: return
        val slot = event.rawSlot
        val uuid = player.uniqueId
        val lastClickTime = playerClickTimes[uuid] ?: currentTick
        val clickDelay = gui::class.clickDelay
        val previousClickDelay = currentTick - lastClickTime
        val clickType = event.click

        event.isCancelled = true

        if (uuid in playerClickTimes.keys && previousClickDelay < clickDelay) {
            return
        }

        playerClickTimes[uuid] = currentTick

        gui.contents.handle(player, slot, clickType, gui)
    }

    @EventHandler(ignoreCancelled = true)
    fun on(event: InventoryOpenEvent) {
        val inventory = event.inventory
        val gui = inventory.holder as? BaseGui ?: return

        gui.update()
    }

    @EventHandler
    fun on(event: InventoryCloseEvent) {
        val inventory = event.inventory
        val holder = inventory.holder

        if (holder !is BaseGui) {
            return
        }

        val player = event.player as? Player ?: return

        playerClickTimes -= player.uniqueId
    }
}