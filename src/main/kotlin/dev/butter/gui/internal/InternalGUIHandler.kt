package dev.butter.gui.internal

import dev.butter.gui.api.annotation.GUISize
import dev.butter.gui.api.annotation.GUITitle
import dev.butter.gui.api.annotation.TypeAlias
import dev.butter.gui.api.base.VerneBaseGUI
import dev.butter.gui.api.type.GUIType.DYNAMIC
import dev.butter.gui.api.type.GUIType.STATIC
import dev.butter.gui.internal.exception.dependency.AlreadyRegisteredException
import dev.butter.gui.internal.exception.dependency.MissingNoArgsConstructorException
import dev.butter.gui.internal.exception.dependency.SingletonRegisteredException
import dev.butter.gui.internal.exception.gui.*
import dev.butter.gui.internal.extensions.*
import dev.butter.gui.internal.listener.PlayerLoginListener
import dev.butter.gui.internal.listener.VerneGUIListener
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

internal typealias GUIClass = KClass<out VerneBaseGUI>
internal typealias AnyClass = KClass<out Any>
internal typealias DependencyInit<T> = (JavaPlugin) -> T
internal typealias PlayerDependencyInit<T> = (Player, JavaPlugin) -> T

internal object InternalGUIHandler {
    private val guiSet: MutableSet<GUIClass> = mutableSetOf()
    private val dynamicGuiSet: MutableSet<GUIClass> = mutableSetOf()
    private val staticGuiSet: MutableSet<GUIClass> = mutableSetOf()
    internal val nonPlayerGuiInstances: MutableSet<VerneBaseGUI> = mutableSetOf()
    internal val playerGuiInstances: MutableMap<UUID, Set<VerneBaseGUI>> = mutableMapOf()
    internal val dependencyMap: MutableMap<AnyClass, DependencyInit<*>> = mutableMapOf()
    internal val playerDependencyMap: MutableMap<AnyClass, PlayerDependencyInit<*>> = mutableMapOf()
    internal val singletonMap: MutableMap<AnyClass, DependencyInit<*>> = mutableMapOf()
    internal lateinit var plugin: JavaPlugin

    internal fun init(plugin: JavaPlugin) {
        this.plugin = plugin

        if (guiSet.isEmpty()) {
            return
        }

        plugin.server.pluginManager.registerEvents(PlayerLoginListener, plugin)
        plugin.server.pluginManager.registerEvents(VerneGUIListener, plugin)

        sortGuiSet()
        initStaticGuis()
        initDynamicGuis()
    }

    internal fun register(gui: GUIClass) = when {
        !gui.hasNoArgsConstructor ->
            throw MissingNoArgsConstructorException(gui)

        !gui.hasAnnotation<TypeAlias>() ->
            throw MissingTypeAliasException(gui)

        !gui.hasAnnotation<GUITitle>() ->
            throw MissingGUITitleException(gui)

        !gui.hasAnnotation<GUISize>() ->
            throw MissingGUISizeException(gui)

        else -> guiSet += gui
    }

    internal fun registerDependency(dependency: AnyClass) = when {
        !dependency.hasNoArgsConstructor ->
            throw MissingNoArgsConstructorException(dependency)

        dependency in dependencyMap.keys ->
            throw SingletonRegisteredException(dependency, false)

        dependency in singletonMap.keys ->
            throw SingletonRegisteredException(dependency, true)

        else -> dependencyMap +=
            dependency to { dependency.noArgsConstructor()!!.call() }
    }

    internal fun <D : KClass<T>, T : Any> registerDependency(
        dependency: D,
        init: DependencyInit<T>,
    ) = if (dependency in dependencyMap.keys) throw AlreadyRegisteredException(dependency)
        else dependencyMap += dependency to init

    internal fun <D : KClass<T>, T : Any> registerDependency(
        dependency: D,
        init: PlayerDependencyInit<T>,
    ) = if (dependency in playerDependencyMap.keys) throw AlreadyRegisteredException(dependency)
        else playerDependencyMap += dependency to init

    internal fun registerSingleton(dependency: AnyClass) = when {
        !dependency.hasNoArgsConstructor ->
            throw MissingNoArgsConstructorException(dependency)

        dependency in dependencyMap.keys ->
            throw SingletonRegisteredException(dependency, false)

        dependency in singletonMap.keys ->
            throw SingletonRegisteredException(dependency, true)

        else -> {
            val constructor = dependency.noArgsConstructor()!!
            val instance = constructor.call()

            singletonMap += dependency to { instance }
        }
    }

    @Suppress("UNCHECKED_CAST")
    internal fun <G : VerneBaseGUI> getMapping(
        gui: KClass<G>
    ) = if (gui in staticGuiSet) throw StaticGUIRequestException(gui)
        else playerGuiInstances.mapValues { guiList ->
            guiList.find(gui::isInstance) as? G
                ?: throw UnregisteredGUIException(gui)
        }

    @Suppress("UNCHECKED_CAST")
    internal fun <G : VerneBaseGUI> getStatic(
        gui: KClass<G>
    ) = if (gui in dynamicGuiSet) throw DynamicGUIRequestException(gui)
        else nonPlayerGuiInstances.find(gui::isInstance) as? G
            ?: throw UnregisteredGUIException(gui)

    internal fun getGuis(player: Player) = playerGuiInstances[player.uniqueId]!!.toSet()

    @Suppress("UNCHECKED_CAST")
    internal fun <G : VerneBaseGUI> get(
        gui: KClass<G>,
        player: Player,
    ) = this.getGuis(player).find(gui::isInstance) as? G
            ?: throw UnregisteredGUIException(gui)

    private fun sortGuiSet() = guiSet
        .associateWithNotNull<GUIClass, TypeAlias>(GUIClass::findAnnotation)
        .mapValues(TypeAlias::type)
        .onEachKey(GUIClass::validateDependencies)
        .forEach { (gui, type) ->
            when (type) {
                DYNAMIC -> dynamicGuiSet += gui
                STATIC -> staticGuiSet += gui
            }
        }

    private fun initStaticGuis() = staticGuiSet
        .asSequence()
        .map(GUIClass::createInstance)
        .onEach(VerneBaseGUI::injectNonPlayerDependencies)
        .onEach { gui -> gui.init(null, plugin) }
        .forEach(nonPlayerGuiInstances::add)

    private fun initDynamicGuis() = plugin.server.onlinePlayers
        .forEach { player -> player.registerPlayer() }

    internal fun Player.registerPlayer() {
        playerGuiInstances += this.uniqueId to dynamicGuiSet
            .asSequence()
            .map(GUIClass::createInstance)
            .onEach(VerneBaseGUI::injectNonPlayerDependencies)
            .onEach { gui -> gui.injectPlayerDependencies(this) }
            .onEach { gui -> gui.init(this, plugin) }
            .toSet()
    }
}