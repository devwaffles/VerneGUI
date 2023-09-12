package dev.butter.gui.api

import dev.butter.gui.api.base.VerneBaseGUI
import dev.butter.gui.internal.*
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import kotlin.reflect.KClass

/**
 * The VerneGUI interface is the main interface for the VerneGUI API.
 * It is used to register GUIs, dependencies, and retrieve GUIs.
 * Dependencies can be registered as a singleton or non-singleton.
 * Dependencies can also be registered with a custom init function (non-singleton),
 * that can either be player-dependent or not.
 *
 * The VerneGUI interface is implemented by the InternalVerneGUI class,
 * and can be accessed through the VerneGUI.Companion.getInstance method.
 */
interface VerneGUI {

    /**
     * The init method is used to initialize the VerneGUI API.
     * Call this method within your plugin's onEnable logic and
     * pass in your plugin instance after you have registered
     * all of your GUIs and dependencies.
     *
     * @param plugin The plugin that is initializing the VerneGUI API.
     * @return Unit
     */
    fun init(plugin: JavaPlugin)

    /**
     * The register method is used to register GUIs.
     * Pass in the GUI class that you want to register
     * with their associated kotlin class ensuring that
     * they extend the VerneBaseGUI class, have the
     * GUITitle, GUISize, and TypeAlias annotations, and
     * have all dependencies registered.
     *
     * @see dev.butter.gui.api.base.VerneBaseGUI
     * @see dev.butter.gui.api.annotation.GUITitle
     * @see dev.butter.gui.api.annotation.GUISize
     * @see dev.butter.gui.api.annotation.TypeAlias
     *
     * @param guis The GUI kotlin classes to register.
     * @return Unit
     */
    fun register(vararg guis: GUIClass)

    /**
     * The registerDependency method is used to register
     * dependencies. Pass in the dependency classes that you
     * want to register with their associated kotlin class
     * ensuring that they all have a no args constructor.
     *
     * @see dev.butter.gui.api.annotation.Dependency
     *
     * @param dependencies The dependency kotlin classes to register.
     * @return Unit
     */
    fun registerDependency(vararg dependencies: AnyClass)

    /**
     * The registerDependency method is used to register
     * dependencies that either have a custom init function or
     * do not have no args constructors. Pass in the dependency
     * kotlin class along with the init function that will be
     * used to construct the dependency instances.
     *
     * @see dev.butter.gui.api.annotation.Dependency
     *
     * @param dependency The dependency kotlin class to register.
     * @param init The init function that will be used to construct the dependency instances.
     * @return Unit
     */
    fun <D : KClass<T>, T : Any> registerDependency(
        dependency: D,
        init: DependencyInit<T>,
    )

    /**
     * The registerDependency method is used to register
     * dependencies that either have a custom init function or
     * do not have no any args constructors. Pass in the dependency
     * kotlin class along with the init function that will be
     * used to construct the dependency instances.
     *
     * @see dev.butter.gui.api.annotation.Dependency
     *
     * @param dependency The dependency kotlin class to register.
     * @param init The init function that will be used to construct the dependency instances.
     * @return Unit
     */
    fun <D : KClass<T>, T : Any> registerDependency(
        dependency: D,
        init: PlayerDependencyInit<T>,
    )

    /**
     * The registerSingleton method is used to register
     * dependencies that have a no args constructor and
     * should only have one instance that is shared between
     * dependency injections.
     *
     * @see dev.butter.gui.api.annotation.Dependency
     *
     * @param dependency The dependency kotlin class to register.
     * @return Unit
     */
    fun registerSingleton(dependency: AnyClass)

    /**
     * The getMapping method is used to retrieve a mapping
     * of all current online players to their respective GUI
     * instances based on the GUI kotlin class that is passed in.
     *
     * @param gui The GUI kotlin class to retrieve the mapping for.
     * @return A mapping of all current online players to their respective GUI instances.
     */
    fun <G : VerneBaseGUI> getMapping(gui: KClass<G>): Map<UUID, G>

    /**
     * The getStatic method is used to retrieve the static
     * instance of the GUI kotlin class that is passed in.
     *
     * @param gui The GUI kotlin class to retrieve the static instance for.
     * @return The static instance of the GUI kotlin class.
     */
    fun <G : VerneBaseGUI> getStatic(gui: KClass<G>): G

    /**
     * The getGuis method is used to retrieve a set of all
     * registered dynamic GUI instances that belongs to the
     * player that is passed in.
     *
     * @param player The player to retrieve the dynamic GUI instances for.
     * @return A set of all registered dynamic GUI instances that belongs to the player.
     */
    fun getGuis(player: Player): Set<VerneBaseGUI>

    /**
     * The get method is used to retrieve the dynamic GUI instance
     * that belongs to the player that is passed in.
     *
     * @param gui The GUI kotlin class to retrieve the dynamic GUI instance for.
     * @param player The player to retrieve the dynamic GUI instance for.
     * @return The dynamic GUI instance that belongs to the player.
     */
    operator fun <G : VerneBaseGUI> get(
        gui: KClass<G>,
        player: Player,
    ): G

    companion object {
        /**
         * The getInstance method is used to retrieve the VerneGUI
         * instance that is implemented by the InternalVerneGUI class.
         *
         * @return The VerneGUI instance.
         */
        fun getInstance(): VerneGUI = InternalVerneGUI
    }
}