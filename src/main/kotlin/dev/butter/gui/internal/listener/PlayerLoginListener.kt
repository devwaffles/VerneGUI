package dev.butter.gui.internal.listener

import dev.butter.gui.internal.InternalGUIHandler.playerGuiInstances
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

        if (uuid in playerGuiInstances.keys) {
            return
        }

        player.registerPlayer()
    }
}