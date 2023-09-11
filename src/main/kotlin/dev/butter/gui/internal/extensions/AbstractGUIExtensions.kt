package dev.butter.gui.internal.extensions

import dev.butter.gui.api.annotation.GUISize
import dev.butter.gui.api.annotation.GUITitle
import dev.butter.gui.api.base.GUIContents
import dev.butter.gui.api.base.VerneBaseGUI
import dev.butter.gui.api.type.GUIType
import dev.butter.gui.api.type.GUIType.STATIC
import dev.butter.gui.internal.InternalGUIHandler.dependencyMap
import dev.butter.gui.internal.InternalGUIHandler.playerDependencyMap
import dev.butter.gui.internal.InternalGUIHandler.plugin
import dev.butter.gui.internal.InternalGUIHandler.singletonMap
import dev.butter.gui.internal.exception.dependency.PlayerDependencyInStaticGUIException
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

internal fun <G : VerneBaseGUI> KClass<G>.validateDependencies(type: GUIType) =
    this.annotatedDependencyFields()
        .filterValues(playerDependencyMap.keys::contains)
        .forEach { (field, clazz) ->
            if (type == STATIC) {
                throw PlayerDependencyInStaticGUIException(clazz, this, field.name)
            }
        }

internal inline fun <reified G : VerneBaseGUI> G.init(
    owner: Player?,
    plugin: JavaPlugin,
) {
    val rows = G::class.findAnnotation<GUISize>()!!.rows
    val title = G::class.findAnnotation<GUITitle>()!!.value

    this.gui = plugin.server.createInventory(this, rows * 9, title)
    this.contents = GUIContents(G::class, this.gui)
    this.owner = owner
}

internal fun VerneBaseGUI.injectNonPlayerDependencies() {
    val dependencyGraph = dependencyMap + singletonMap

    this::class
        .annotatedDependencyFields()
        .filterValues(dependencyGraph.keys::contains)
        .mapValues { dep -> dependencyGraph[dep]!! }
        .forEach { (field, init) ->
            field.isAccessible = true
            field.set(this, init.invoke(plugin))
        }
}

internal fun VerneBaseGUI.injectPlayerDependencies(player: Player) = this::class
    .annotatedDependencyFields()
    .filterValues(playerDependencyMap.keys::contains)
    .mapValues { dep -> playerDependencyMap[dep]!! }
    .forEach { (field, init) ->
        field.isAccessible = true
        field.set(this, init.invoke(player, plugin))
    }

internal fun VerneBaseGUI.update() {
    this.contents.clear()
    this.createContents()
    this.contents.init()
}