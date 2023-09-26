package dev.butter.gui.internal

import com.google.inject.Injector
import dev.butter.gui.api.base.BaseGUI
import dev.butter.gui.api.type.AnyClass
import dev.butter.gui.api.type.DependencyInit
import dev.butter.gui.api.type.GUIClass
import dev.butter.gui.api.type.GUIType.DYNAMIC
import dev.butter.gui.api.type.GUIType.STATIC
import dev.butter.gui.api.type.PlayerDependencyInit
import dev.butter.gui.internal.InternalGUIHandler.dynamicGuis
import dev.butter.gui.internal.InternalGUIHandler.playerGuiInstances
import dev.butter.gui.internal.InternalGUIHandler.plugin
import dev.butter.gui.internal.extensions.*
import dev.butter.gui.internal.listener.PlayerLoginListener
import dev.butter.gui.internal.listener.VerneGUIListener
import dev.butter.gui.internal.updater.GUIUpdater
import dev.butter.gui.internal.validation.*
import dev.butter.gui.internal.validation.DependencyType.*
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

internal object InternalGUIHandler {
    private val guis: MutableSet<GUIClass> = mutableSetOf()
    internal val dynamicGuis: MutableSet<GUIClass> = mutableSetOf()
    internal val staticGuis: MutableSet<GUIClass> = mutableSetOf()
    internal val guiDelays: MutableMap<GUIClass, Long> = mutableMapOf()
    internal val nonPlayerGuiInstances: MutableSet<BaseGUI> = mutableSetOf()
    internal val playerGuiInstances: MutableMap<UUID, Set<BaseGUI>> = mutableMapOf()
    internal val nonPlayerDependencies: MutableMap<AnyClass, DependencyInit<*>> = mutableMapOf()
    internal val playerDependencies: MutableMap<AnyClass, PlayerDependencyInit<*>> = mutableMapOf()
    internal val singletons: MutableMap<AnyClass, DependencyInit<*>> = mutableMapOf()
    internal lateinit var plugin: JavaPlugin
    internal lateinit var injector: Injector

    internal fun isInitialized() = ::plugin.isInitialized

    internal fun toInjectGuice() = ::injector.isInitialized

    internal fun init(plugin: JavaPlugin, injector: Injector? = null) {
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

        plugin.server.scheduler.runTaskTimer(plugin, GUIUpdater as Runnable, 0L, 1L)
        plugin.server.pluginManager.registerEvents(PlayerLoginListener, plugin)
        plugin.server.pluginManager.registerEvents(VerneGUIListener, plugin)
    }

    internal fun register(gui: GUIClass) {
        validateUninitialized()
        validate(gui)

        guis += gui
        guiDelays += gui to gui.clickDelay
    }

    internal fun registerDependency(dependency: AnyClass) {
        validateUninitialized()
        validate(DEPENDENCY, dependency, true)

        nonPlayerDependencies +=
            dependency to { dependency.noArgsConstructor()!!.call() }
    }

    internal fun <D : KClass<T>, T : Any> registerDependency(
        dependency: D,
        init: DependencyInit<T>,
    ) {
        validateUninitialized()
        validate(DEPENDENCY, dependency, false)

        nonPlayerDependencies += dependency to init
    }

    internal fun <D : KClass<T>, T : Any> registerPlayerDependency(
        dependency: D,
        init: PlayerDependencyInit<T>,
    ) {
        validateUninitialized()
        validate(PLAYER_DEPENDENCY, dependency, false)

        playerDependencies += dependency to init
    }

    internal fun registerSingleton(dependency: AnyClass) {
        validateUninitialized()
        validate(SINGLETON, dependency, true)

        val constructor = dependency.noArgsConstructor()!!
        val instance = constructor.call()

        singletons += dependency to { instance }
    }

    @Suppress("UNCHECKED_CAST")
    internal fun <G : BaseGUI> getMapping(
        gui: KClass<G>
    ): Map<UUID, G> {
        validateRegistered(gui)
        validateNonStatic(gui)

        return playerGuiInstances.mapValues { guiList ->
            guiList.find(gui::isInstance) as G
        }
    }

    @Suppress("UNCHECKED_CAST")
    internal fun <G : BaseGUI> getStatic(
        gui: KClass<G>
    ): G {
        validateRegistered(gui)
        validateStatic(gui)

        return nonPlayerGuiInstances.find(gui::isInstance) as G
    }

    internal fun getGuis(player: Player) = playerGuiInstances[player.uniqueId]!!.toSet()

    @Suppress("UNCHECKED_CAST")
    internal fun <G : BaseGUI> get(
        gui: KClass<G>,
        player: Player,
    ): G {
        validateRegistered(gui)
        validateNonStatic(gui)

        return this.getGuis(player).find(gui::isInstance) as G
    }

    private fun sortGuiSet() = guis
        .associateWith(GUIClass::type)
        .onEachKey(GUIClass::validateDependencies)
        .forEach { (gui, type) ->
            when (type) {
                DYNAMIC -> dynamicGuis += gui
                STATIC -> staticGuis += gui
            }
        }

    private fun initStaticGuis() = staticGuis
        .map(GUIClass::createInstance)
        .onEach { gui -> gui.init(null, plugin) }
        .forEach(nonPlayerGuiInstances::add)

    private fun initDynamicGuis() = plugin.server.onlinePlayers
        .forEach(Player::registerPlayer)
}

internal fun Player.registerPlayer() {
    playerGuiInstances += this.uniqueId to dynamicGuis
        .asSequence()
        .map(GUIClass::createInstance)
        .onEach { gui -> gui.init(this, plugin) }
        .toSet()
}