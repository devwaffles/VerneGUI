package dev.butter.gui.internal.extensions

import dev.butter.gui.api.base.BaseGUI
import dev.butter.gui.api.base.GUIContents
import dev.butter.gui.api.base.update
import dev.butter.gui.internal.InternalGUIHandler.toInjectGuice
import dev.butter.gui.internal.InternalGUIHandler.injector
import dev.butter.gui.internal.InternalGUIHandler.nonPlayerDependencies
import dev.butter.gui.internal.InternalGUIHandler.playerDependencies
import dev.butter.gui.internal.InternalGUIHandler.singletons
import org.bukkit.ChatColor.BOLD
import org.bukkit.ChatColor.translateAlternateColorCodes
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

internal fun BaseGUI.init(
    owner: Player?,
    plugin: JavaPlugin,
) {
    val title = this::class.title

    this.plugin = plugin

    if (toInjectGuice()) {
        injector.injectMembers(this)
    }

    injectNonPlayerDependencies()

    if (owner != null) {
        this.owner = owner
        injectPlayerDependencies(owner)
    }

    this.contents = GUIContents(this)
    this.gui = plugin.server.createInventory(this, this::class.rows * 9, "${title.color}${if (title.bold) BOLD else ""}${translateAlternateColorCodes('&', title.value)}")

    this.update()
}

internal fun BaseGUI.injectNonPlayerDependencies() = this::class
    .annotatedDependencyFields()
    .filterValues((nonPlayerDependencies + singletons).keys::contains)
    .mapValues { dep -> (nonPlayerDependencies + singletons)[dep]!! }
    .forEach { (field, init) ->
        field.isAccessible = true
        field.set(this, init.invoke(plugin))
    }

internal fun BaseGUI.injectPlayerDependencies(player: Player) = this::class
    .annotatedDependencyFields()
    .filterValues(playerDependencies.keys::contains)
    .mapValues { dep -> playerDependencies[dep]!! }
    .forEach { (field, init) ->
        field.isAccessible = true
        field.set(this, init.invoke(player, plugin))
    }
