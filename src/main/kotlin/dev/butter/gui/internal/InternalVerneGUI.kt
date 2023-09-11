package dev.butter.gui.internal

import dev.butter.gui.api.VerneGUI
import dev.butter.gui.api.base.VerneBaseGUI
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import kotlin.reflect.KClass

internal object InternalVerneGUI : VerneGUI {
    override fun init(plugin: JavaPlugin) =
        InternalGUIHandler.init(plugin)

    override fun register(vararg guis: GUIClass) =
        guis.forEach(InternalGUIHandler::register)

    override fun registerDependency(dependency: AnyClass) =
        InternalGUIHandler.registerDependency(dependency)

    override fun <D : KClass<T>, T : Any> registerDependency(
        dependency: D,
        init: DependencyInit<T>,
    ) = InternalGUIHandler.registerDependency(dependency, init)

    override fun <D : KClass<T>, T : Any> registerDependency(
        dependency: D,
        init: PlayerDependencyInit<T>,
    ) = InternalGUIHandler.registerDependency(dependency, init)

    override fun registerSingleton(dependency: AnyClass) =
        InternalGUIHandler.registerSingleton(dependency)

    override fun <G : VerneBaseGUI> getMapping(gui: KClass<G>) =
        InternalGUIHandler.getMapping(gui)

    override fun <G : VerneBaseGUI> getStatic(gui: KClass<G>) =
        InternalGUIHandler.getStatic(gui)

    override fun getGuis(player: Player) =
        InternalGUIHandler.getGuis(player)

    override operator fun <G : VerneBaseGUI> get(
        gui: KClass<G>,
        player: Player,
    ) = InternalGUIHandler.get(gui, player)
}