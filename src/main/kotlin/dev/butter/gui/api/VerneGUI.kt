package dev.butter.gui.api

import dev.butter.gui.api.base.VerneBaseGUI
import dev.butter.gui.internal.*
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import kotlin.reflect.KClass

interface VerneGUI {
    fun get(): VerneGUI = InternalVerneGUI

    fun init(plugin: JavaPlugin)

    fun register(vararg guis: GUIClass)

    fun registerDependency(dependency: AnyClass)

    fun <D : KClass<T>, T : Any> registerDependency(
        dependency: D,
        init: DependencyInit<T>,
    )

    fun <D : KClass<T>, T : Any> registerDependency(
        dependency: D,
        init: PlayerDependencyInit<T>,
    )

    fun registerSingleton(dependency: AnyClass)

    fun <G : VerneBaseGUI> getMapping(gui: KClass<G>): Map<UUID, G>

    fun <G : VerneBaseGUI> getStatic(gui: KClass<G>): G

    fun getGuis(player: Player): Set<VerneBaseGUI>

    operator fun <G : VerneBaseGUI> get(
        gui: KClass<G>,
        player: Player,
    ): G
}