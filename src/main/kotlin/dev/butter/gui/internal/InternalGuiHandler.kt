package dev.butter.gui.internal

import com.google.inject.Injector
import dev.butter.gui.api.base.BaseGui
import dev.butter.gui.api.type.AnyClass
import dev.butter.gui.api.type.DynamicInit
import dev.butter.gui.api.type.GuiClass
import dev.butter.gui.api.type.GuiType.DYNAMIC
import dev.butter.gui.api.type.GuiType.STATIC
import dev.butter.gui.api.type.StaticInit
import dev.butter.gui.internal.InternalGuiHandler.dynamicGuiInstances
import dev.butter.gui.internal.InternalGuiHandler.dynamicGuis
import dev.butter.gui.internal.InternalGuiHandler.plugin
import dev.butter.gui.internal.extensions.init
import dev.butter.gui.internal.extensions.mapValues
import dev.butter.gui.internal.extensions.noArgsConstructor
import dev.butter.gui.internal.extensions.type
import dev.butter.gui.internal.listener.BaseGuiListener
import dev.butter.gui.internal.listener.PlayerLoginListener
import dev.butter.gui.internal.update.AnimatedItemRunnable
import dev.butter.gui.internal.validation.*
import dev.butter.gui.internal.validation.DependencyType.SINGLETON
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

internal object InternalGuiHandler {
    internal val guis: MutableSet<GuiClass> = mutableSetOf()
    internal val dynamicGuis: MutableSet<GuiClass> = mutableSetOf()
    internal val staticGuis: MutableSet<GuiClass> = mutableSetOf()
    internal val staticGuiInstances: MutableSet<BaseGui> = mutableSetOf()
    internal val dynamicGuiInstances: MutableMap<UUID, Set<BaseGui>> = mutableMapOf()
    internal val staticDependencies: MutableMap<AnyClass, StaticInit<*>> = mutableMapOf()
    internal val dynamicDependencies: MutableMap<AnyClass, DynamicInit<*>> = mutableMapOf()
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
        validateUninitialized()
        gui.validate()

        guis += gui
    }

    fun registerDependency(dependency: AnyClass) {
        validateUninitialized()
        validate(DependencyType.STATIC, dependency, true)

        staticDependencies +=
            dependency to { dependency.noArgsConstructor.call() }
    }

    fun <D : KClass<T>, T : Any> registerStatic(
        dependency: D,
        init: StaticInit<T>,
    ) {
        validateUninitialized()
        validate(DependencyType.STATIC, dependency, false)

        staticDependencies += dependency to init
    }

    fun <D : KClass<T>, T : Any> registerDynamic(
        dependency: D,
        init: DynamicInit<T>,
    ) {
        validateUninitialized()
        validate(DependencyType.DYNAMIC, dependency, false)

        dynamicDependencies += dependency to init
    }

    fun registerSingleton(dependency: AnyClass) {
        validateUninitialized()
        validate(SINGLETON, dependency, true)

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

    fun getGuis(player: Player) = dynamicGuiInstances[player.uniqueId]!!.toSet()

    @Suppress("UNCHECKED_CAST")
    fun <G : BaseGui> get(
        gui: KClass<G>,
        player: Player,
    ): G {
        validateRegistered(gui)
        validateNonStatic(gui)

        return this.getGuis(player).find(gui::isInstance) as G
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
        .forEach(Player::registerPlayer)
}

internal fun Player.registerPlayer() {
    dynamicGuiInstances += this.uniqueId to dynamicGuis
        .map(GuiClass::createInstance)
        .onEach { gui -> gui.init(this, plugin) }
        .toSet()
}