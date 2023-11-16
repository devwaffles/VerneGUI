package dev.butter.gui.api.type

import dev.butter.gui.api.base.BaseGui
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import kotlin.reflect.KClass

/**
 * Type aliases for the GUI API.
 */
typealias GuiClass = KClass<out BaseGui>
typealias AnyClass = KClass<out Any>

/**
 * This is a type alias used for initializing static dependencies.
 */
typealias StaticInit<T> = (JavaPlugin) -> T

/**
 * This is a type alias used for initializing dynamic dependencies.
 */
typealias DynamicInit<T> = (Player, JavaPlugin) -> T

typealias GameTicks = Long