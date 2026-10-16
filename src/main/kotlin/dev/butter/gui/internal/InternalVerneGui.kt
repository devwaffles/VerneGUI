package dev.butter.gui.internal

import com.google.inject.Injector
import dev.butter.gui.api.VerneGui
import dev.butter.gui.api.base.BaseGui
import dev.butter.gui.api.type.*
import dev.butter.gui.internal.InternalGuiHandler.DUMMY_GUI
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

    override fun register(guis: Collection<GuiClass>) =
        guis.forEach(InternalGuiHandler::register)

    override fun registerStatic(vararg dependencies: AnyClass) =
        dependencies.forEach(InternalGuiHandler::registerDependency)

    override fun <D : KClass<T>, T : Any> registerStatic(
        dependency: D,
        init: StaticInit<T>,
    ) = InternalGuiHandler.registerStatic(dependency, DUMMY_GUI::class, init)

    override fun <D : KClass<T>, T : Any, G : BaseGui> registerStatic(
        dependency: D,
        gui: KClass<G>,
        init: StaticInit<T>
    ) = InternalGuiHandler.registerStatic(dependency, gui, init)

    override fun <D : KClass<T>, T : Any> registerDynamic(
        dependency: D,
        init: DynamicInit<T>,
    ) = InternalGuiHandler.registerDynamic(dependency, DUMMY_GUI::class, init)

    override fun <D : KClass<T>, T : Any, G : BaseGui> registerDynamic(
        dependency: D,
        gui: KClass<G>,
        init: DynamicInit<T>
    ) = InternalGuiHandler.registerDynamic(dependency, gui, init)

    override fun <D : KClass<T>, T : Any> registerDynamic(
        dependency: D,
        init: PlayerInit<T>,
    ) = InternalGuiHandler.registerDynamic(dependency, DUMMY_GUI::class, init)

    override fun <D : KClass<T>, T : Any, G : BaseGui> registerDynamic(
        dependency: D,
        gui: KClass<G>,
        init: PlayerInit<T>
    ) = InternalGuiHandler.registerDynamic(dependency, gui, init)

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