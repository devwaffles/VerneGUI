package dev.butter.gui.internal.listener

import dev.butter.gui.internal.extensions.register
import dev.butter.gui.internal.extensions.unregister
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority.HIGHEST
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

internal object PlayerLoginListener : Listener {
    @EventHandler(priority = HIGHEST)
    fun on(event: PlayerJoinEvent) =
        event.player.register()

    @EventHandler(priority = HIGHEST)
    fun on(event: PlayerQuitEvent) =
        event.player.unregister()
}