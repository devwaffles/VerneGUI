package dev.butter.gui.api.type

import dev.butter.gui.api.base.BaseGUI
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import kotlin.reflect.KClass

/**
 * Type aliases for the GUI API.
 */
typealias GUIClass = KClass<out BaseGUI>
typealias AnyClass = KClass<out Any>
typealias DependencyInit<T> = (JavaPlugin) -> T
typealias PlayerDependencyInit<T> = (Player, JavaPlugin) -> T