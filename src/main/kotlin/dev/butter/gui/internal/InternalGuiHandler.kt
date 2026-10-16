package dev.butter.gui.internal

import com.google.inject.Injector
import dev.butter.gui.api.base.BaseGui
import dev.butter.gui.api.type.*
import dev.butter.gui.api.type.GuiType.DYNAMIC
import dev.butter.gui.api.type.GuiType.STATIC
import dev.butter.gui.internal.extensions.*
import dev.butter.gui.internal.listener.BaseGuiListener
import dev.butter.gui.internal.listener.PlayerLoginListener
import dev.butter.gui.internal.update.AnimatedItemRunnable
import dev.butter.gui.internal.validation.*
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

internal object InternalGuiHandler {
    internal val DUMMY_GUI: BaseGui = object : BaseGui() {}

    internal val guis: MutableSet<GuiClass> = mutableSetOf()
    internal val dynamicGuis: MutableSet<GuiClass> = mutableSetOf()
    internal val staticGuis: MutableSet<GuiClass> = mutableSetOf()
    internal val staticGuiInstances: MutableSet<BaseGui> = mutableSetOf()
    internal val dynamicGuiInstances: MutableMap<UUID, Set<BaseGui>> = mutableMapOf()
    internal val staticDependencies: MutableMap<AnyClass, MutableMap<KClass<out BaseGui>, StaticInit<*>>> = mutableMapOf()
    internal val dynamicDependencies: MutableMap<AnyClass, MutableMap<KClass<out BaseGui>, DynamicInit<*>>> = mutableMapOf()
    internal val singletons: MutableMap<AnyClass, StaticInit<*>> = mutableMapOf()
    internal lateinit var plugin: JavaPlugin
    internal lateinit var injector: Injector

    fun isInitialized() = ::plugin.isInitialized

    fun toInjectGuice() = ::injector.isInitialized

    fun init(
        plugin: JavaPlugin,
        injector: Injector? = null,
    ) {
        if (guis.isEmpty()) {
            return
        }

        this.plugin = plugin

        if (injector != null) {
            this.injector = injector
        }

        sortGuiSet()
        initStaticGuis()
        initDynamicGuis()

        AnimatedItemRunnable.init()

        plugin.server.scheduler.runTaskTimer(plugin, AnimatedItemRunnable as Runnable, 0L, 1L)
        plugin.server.pluginManager.registerEvents(PlayerLoginListener, plugin)
        plugin.server.pluginManager.registerEvents(BaseGuiListener, plugin)
    }

    fun register(gui: GuiClass) {
        if (gui == DUMMY_GUI::class) {
            return
        }

        validateUninitialized()
        gui.validate()

        guis += gui
    }

    fun registerDependency(dependency: AnyClass) {
        validateUninitialized()
        validateNoArgsConstructor(dependency)

        staticDependencies.getOrPut(
            dependency,
            ::mutableMapOf
        )[DUMMY_GUI::class] = { dependency.noArgsConstructor.call() }
    }

    fun <D : KClass<T>, T : Any, G : BaseGui> registerStatic(
        dependency: D,
        gui: KClass<G>,
        init: StaticInit<T>,
    ) {
        validateUninitialized()

        staticDependencies.getOrPut(
            dependency,
            ::mutableMapOf
        )[gui] = init
    }

    fun <D : KClass<T>, T : Any, G : BaseGui> registerDynamic(
        dependency: D,
        gui: KClass<G>,
        init: DynamicInit<T>,
    ) {
        validateUninitialized()

        dynamicDependencies.getOrPut(
            dependency,
            ::mutableMapOf,
        )[gui] = init
    }

    fun <D : KClass<T>, T : Any, G : BaseGui> registerDynamic(
        dependency: D,
        gui: KClass<G>,
        init: PlayerInit<T>,
    ) {
        validateUninitialized()

        dynamicDependencies.getOrPut(
            dependency,
            ::mutableMapOf,
        )[gui] = { player, _ -> init(player) }
    }

    fun registerSingleton(dependency: AnyClass) {
        validateUninitialized()
        validateNoArgsConstructor(dependency)

        val constructor = dependency.noArgsConstructor
        val instance = constructor.call()

        singletons += dependency to { instance }
    }

    @Suppress("UNCHECKED_CAST")
    fun <G : BaseGui> getMapping(gui: KClass<G>): Map<UUID, G> {
        validateRegistered(gui)
        validateNonStatic(gui)

        return dynamicGuiInstances.mapValues { guiList ->
            guiList.find(gui::isInstance) as G
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <G : BaseGui> getStatic(gui: KClass<G>): G {
        validateRegistered(gui)
        validateStatic(gui)

        return staticGuiInstances.find(gui::isInstance) as G
    }

    fun getGuis(player: Player) = dynamicGuiInstances[player.uniqueId]!!
        .toSet()

    @Suppress("UNCHECKED_CAST")
    fun <G : BaseGui> get(
        gui: KClass<G>,
        player: Player,
    ): G {
        validateRegistered(gui)
        validateNonStatic(gui)

        return this
            .getGuis(player)
            .find(gui::isInstance) as G
    }

    private fun sortGuiSet() = guis
        .onEach(GuiClass::validateDependencies)
        .associateWith(GuiClass::type)
        .forEach { (gui, type) ->
            when (type) {
                DYNAMIC -> dynamicGuis += gui
                STATIC -> staticGuis += gui
            }
        }

    private fun initStaticGuis() = staticGuis
        .map(GuiClass::createInstance)
        .onEach { gui -> gui.init(null, plugin) }
        .forEach(staticGuiInstances::add)

    private fun initDynamicGuis() = plugin.server.onlinePlayers
        .forEach(Player::register)
}