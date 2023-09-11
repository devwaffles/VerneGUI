package dev.butter.gui.internal.listener

import dev.butter.gui.internal.InternalGUIHandler.registerPlayer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

internal object PlayerLoginListener : Listener {
    @EventHandler
    fun on(event: PlayerJoinEvent) = event.player.registerPlayer()
}