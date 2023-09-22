package dev.butter.gui.internal.types

import dev.butter.gui.api.base.VerneBaseGUI
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import kotlin.reflect.KClass

internal typealias GUIClass = KClass<out VerneBaseGUI>
internal typealias AnyClass = KClass<out Any>
internal typealias DependencyInit<T> = (JavaPlugin) -> T
internal typealias PlayerDependencyInit<T> = (Player, JavaPlugin) -> T