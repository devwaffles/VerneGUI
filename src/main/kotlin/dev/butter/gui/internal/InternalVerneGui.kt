package dev.butter.gui.internal

import com.google.inject.Injector
import dev.butter.gui.api.VerneGui
import dev.butter.gui.api.base.BaseGui
import dev.butter.gui.api.type.AnyClass
import dev.butter.gui.api.type.DynamicInit
import dev.butter.gui.api.type.GuiClass
import dev.butter.gui.api.type.StaticInit
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import kotlin.reflect.KClass

internal object InternalVerneGui : VerneGui {
    override fun init(
        plugin: JavaPlugin,
        injector: Injector?,
    ) = InternalGuiHandler.init(plugin, injector)

    override fun register(vararg guis: GuiClass) =
        guis.forEach(InternalGuiHandler::register)

    override fun registerStatic(vararg dependencies: AnyClass) =
        dependencies.forEach(InternalGuiHandler::registerDependency)

    override fun <D : KClass<T>, T : Any> registerStatic(
        dependency: D,
        init: StaticInit<T>,
    ) = InternalGuiHandler.registerStatic(dependency, init)

    override fun <D : KClass<T>, T : Any> registerDynamic(
        dependency: D,
        init: DynamicInit<T>,
    ) = InternalGuiHandler.registerDynamic(dependency, init)

    override fun <D : KClass<T>, T : Any> registerStatic(
        vararg dependencyPair: Pair<D, StaticInit<T>>
    ) = dependencyPair.forEach { (dependency, pair) ->
        registerStatic(dependency, pair)
    }

    override fun <D : KClass<T>, T : Any> registerDynamic(
        vararg dependencyPair: Pair<D, DynamicInit<T>>
    ) = dependencyPair.forEach { (dependency, pair) ->
        registerDynamic(dependency, pair)
    }

    override fun registerSingleton(dependency: AnyClass) =
        InternalGuiHandler.registerSingleton(dependency)

    override fun <G : BaseGui> getMapping(gui: KClass<G>) =
        InternalGuiHandler.getMapping(gui)

    override operator fun <G : BaseGui> get(gui: KClass<G>) =
        InternalGuiHandler.getStatic(gui)

    override operator fun get(player: Player) =
        InternalGuiHandler.getGuis(player)

    override operator fun <G : BaseGui> get(
        gui: KClass<G>,
        player: Player,
    ) = InternalGuiHandler.get(gui, player)
}