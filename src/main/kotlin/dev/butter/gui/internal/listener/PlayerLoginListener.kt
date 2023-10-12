package dev.butter.gui.internal.listener

import dev.butter.gui.internal.InternalGuiHandler.dynamicGuiInstances
import dev.butter.gui.internal.registerPlayer
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority.HIGHEST
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

internal object PlayerLoginListener : Listener {
    @EventHandler(priority = HIGHEST)
    fun on(event: PlayerJoinEvent) {
        val player = event.player
        val uuid = player.uniqueId

        if (uuid in dynamicGuiInstances.keys) {
            return
        }

        player.registerPlayer()
    }
}