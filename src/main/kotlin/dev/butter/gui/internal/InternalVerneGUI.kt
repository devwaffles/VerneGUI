package dev.butter.gui.internal

import dev.butter.gui.api.VerneGUI
import dev.butter.gui.api.base.BaseGUI
import dev.butter.gui.internal.types.AnyClass
import dev.butter.gui.internal.types.DependencyInit
import dev.butter.gui.internal.types.GUIClass
import dev.butter.gui.internal.types.PlayerDependencyInit
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import kotlin.reflect.KClass

internal object InternalVerneGUI : VerneGUI {
    override fun init(plugin: JavaPlugin) =
        InternalGUIHandler.init(plugin)

    override fun register(vararg guis: GUIClass) =
        guis.forEach(InternalGUIHandler::register)

    override fun registerDependency(vararg dependencies: AnyClass) =
        dependencies.forEach(InternalGUIHandler::registerDependency)

    override fun <D : KClass<T>, T : Any> registerDependency(
        dependency: D,
        init: DependencyInit<T>,
    ) = InternalGUIHandler.registerDependency(dependency, init)

    override fun <D : KClass<T>, T : Any> registerPlayerDependency(
        dependency: D,
        init: PlayerDependencyInit<T>,
    ) = InternalGUIHandler.registerPlayerDependency(dependency, init)

    override fun <D : KClass<T>, T : Any> registerDependency(
        vararg dependencyPair: Pair<D, DependencyInit<T>>
    ) = dependencyPair.forEach { (dependency, pair) ->
        registerDependency(dependency, pair)
    }

    override fun <D : KClass<T>, T : Any> registerPlayerDependency(
        vararg dependencyPair: Pair<D, PlayerDependencyInit<T>>
    ) = dependencyPair.forEach { (dependency, pair) ->
        registerPlayerDependency(dependency, pair)
    }

    override fun registerSingleton(dependency: AnyClass) =
        InternalGUIHandler.registerSingleton(dependency)

    override fun <G : BaseGUI> getMapping(gui: KClass<G>) =
        InternalGUIHandler.getMapping(gui)

    override fun <G : BaseGUI> getStatic(gui: KClass<G>) =
        InternalGUIHandler.getStatic(gui)

    override fun getGuis(player: Player) =
        InternalGUIHandler.getGuis(player)

    override operator fun <G : BaseGUI> get(
        gui: KClass<G>,
        player: Player,
    ) = InternalGUIHandler.get(gui, player)
}