package dev.butter.gui.internal.extensions

import dev.butter.gui.api.annotation.GUISize
import dev.butter.gui.api.annotation.GUITitle
import dev.butter.gui.api.annotation.TypeAlias
import dev.butter.gui.api.base.BaseGUI
import dev.butter.gui.api.base.GUIContents
import dev.butter.gui.api.item.Animated
import dev.butter.gui.api.item.GUIItem
import dev.butter.gui.api.type.GUIType.STATIC
import dev.butter.gui.internal.InternalGUIHandler.dependencyMap
import dev.butter.gui.internal.InternalGUIHandler.playerDependencyMap
import dev.butter.gui.internal.InternalGUIHandler.plugin
import dev.butter.gui.internal.InternalGUIHandler.singletonMap
import dev.butter.gui.internal.exception.dependency.PlayerDependencyInStaticGUIException
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

internal fun <G : KClass<out BaseGUI>> G.validateDependencies() =
    this.annotatedDependencyFields()
        .filterValues(playerDependencyMap.keys::contains)
        .forEach { (field, clazz) ->
            if (this.findAnnotation<TypeAlias>()!!.type == STATIC) {
                throw PlayerDependencyInStaticGUIException(clazz, this, field.name)
            }
        }

internal fun <G : BaseGUI> G.init(
    owner: Player?,
    plugin: JavaPlugin,
) {
    val size = this::class.findAnnotation<GUISize>()!!
    val title = this::class.findAnnotation<GUITitle>()!!

    this.gui = plugin.server.createInventory(this, size.rows * 9, "${title.color}${if (title.bold) ChatColor.BOLD else ""}${ChatColor.translateAlternateColorCodes('&', title.value)}")
    this.contents = GUIContents(this)
    this.owner = owner

    this.createContents()
    this.contents.init()
}

internal fun <G : BaseGUI> G.injectNonPlayerDependencies() {
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

internal inline fun <reified G : BaseGUI> G.injectPlayerDependencies(player: Player) = G::class
    .annotatedDependencyFields()
    .filterValues(playerDependencyMap.keys::contains)
    .mapValues { dep -> playerDependencyMap[dep]!! }
    .forEach { (field, init) ->
        field.isAccessible = true
        field.set(this, init.invoke(player, plugin))
    }

@Suppress("UNCHECKED_CAST")
internal fun <G : BaseGUI> G.update() {
    val items = this.contents.items
    val animatedItems = items.filterIsInstance<Animated>() as List<GUIItem>

    this.contents.init()
}