package dev.butter.gui.api

import com.google.inject.Injector
import dev.butter.gui.api.annotation.*
import dev.butter.gui.api.base.BaseGui
import dev.butter.gui.api.type.AnyClass
import dev.butter.gui.api.type.DynamicInit
import dev.butter.gui.api.type.GuiClass
import dev.butter.gui.api.type.StaticInit
import dev.butter.gui.internal.InternalVerneGui
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
interface VerneGui {

    /**
     * The init method is used to initialize the VerneGUI API.
     * Call this method within your plugin's onEnable logic and
     * pass in your plugin instance after you have registered
     * all of your GUIs and dependencies. If you are using
     * the Guice dependency injection framework, you can also
     * pass in your injector instance, and the VerneGUI API
     * will use it to inject dependencies.
     *
     * @param plugin The plugin that is initializing the VerneGUI API.
     * @param injector The Guice injector instance.
     *
     * @return Unit
     */
    fun init(plugin: JavaPlugin, injector: Injector? = null)

    /**
     * The register method is used to register GUIs.
     * Pass in the GUI class that you want to register
     * with their associated kotlin class ensuring that
     * they extend the BaseGui class, have the
     * GUITitle, GUISize, and TypeAlias annotations, and
     * have all dependencies registered.
     *
     * @see BaseGui
     * @see GuiTitle
     * @see GuiSize
     * @see TypeAlias
     * @see ClickDelay
     *
     * @param guis The GUI kotlin classes to register.
     *
     * @return Unit
     */
    fun register(vararg guis: GuiClass)

    /**
     * The register method is used to register GUIs.
     * Pass in the GUI class that you want to register
     * with their associated kotlin class ensuring that
     * they extend the BaseGui class, have the
     * GUITitle, GUISize, and TypeAlias annotations, and
     * have all dependencies registered.
     *
     * @see BaseGui
     * @see GuiTitle
     * @see GuiSize
     * @see TypeAlias
     * @see ClickDelay
     *
     * @param guis The GUI kotlin classes to register.
     *
     * @return Unit
     */
    fun register(guis: Collection<GuiClass>)

    /**
     * The registerDependency method is used to register
     * dependencies. Pass in the dependency classes that you
     * want to register with their associated kotlin class
     * ensuring that they all have a no args constructor.
     *
     * @see Dependency
     *
     * @param dependencies The dependency kotlin classes to register.
     *
     * @return Unit
     */
    fun registerStatic(vararg dependencies: AnyClass)

    /**
     * The registerDependency method is used to register
     * dependencies that either have a custom init function or
     * do not have no args constructors. Pass in the dependency
     * kotlin class along with the init function that will be
     * used to construct the dependency instances.
     *
     * @see Dependency
     *
     * @param dependency The dependency kotlin class to register.
     * @param init The init function that will be used to construct the static instance.
     *
     * @return Unit
     */
    fun <D : KClass<T>, T : Any> registerStatic(
        dependency: D,
        init: StaticInit<T>,
    )

    /**
     * The registerDependency method is used to register
     * dependencies that either have a custom init function or
     * do not have no any args constructors. Pass in the dependency
     * kotlin class along with the init function that will be
     * used to construct the dependency instances.
     *
     * @see Dependency
     *
     * @param dependency The dependency kotlin class to register.
     * @param init The init function that will be used to construct the dynamic instance.
     *
     * @return Unit
     */
    fun <D : KClass<T>, T : Any> registerDynamic(
        dependency: D,
        init: DynamicInit<T>,
    )

    /**
     * The registerSingleton method is used to register
     * dependencies that have a no args constructor and
     * should only have one instance that is shared between
     * dependency injections.
     *
     * @see Dependency
     *
     * @param dependency The dependency kotlin class to register as a singleton.
     *
     * @return Unit
     */
    fun registerSingleton(dependency: AnyClass)

    /**
     * The getMapping method is used to retrieve a mapping
     * of all registered players to their respective GUI
     * instances based on the GUI kotlin class that is passed in.
     *
     * @param gui The GUI kotlin class to retrieve the mapping for.
     *
     * @return A mapping of all registered players to their respective GUI instances.
     */
    fun <G : BaseGui> getMapping(gui: KClass<G>): Map<UUID, G>

    /**
     * This get method is used to retrieve the static
     * instance of the GUI kotlin class that is passed in.
     *
     * @param gui The GUI kotlin class to retrieve the static instance for.
     *
     * @return The GUI kotlin class.
     */
    operator fun <G : BaseGui> get(gui: KClass<G>): G

    /**
     * This get method is used to retrieve a set of all
     * registered dynamic GUI instances that belongs to the
     * player that is passed in.
     *
     * @param player The player to retrieve the dynamic GUI instances for.
     *
     * @return A set of all registered dynamic GUI instances that belongs to the player.
     */
    operator fun get(player: Player): Set<BaseGui>

    /**
     * The get method is used to retrieve the dynamic GUI instance
     * that belongs to the player that is passed in.
     *
     * @param gui The GUI kotlin class to retrieve the dynamic GUI instance for.
     * @param player The player to retrieve the dynamic GUI instance for.
     * @return The dynamic GUI instance that belongs to the player.
     */
    operator fun <G : BaseGui> get(
        gui: KClass<G>,
        player: Player,
    ): G

    companion object {
        /**
         * The get method is used to retrieve the VerneGUI
         * instance that is implemented by the InternalVerneGUI object.
         *
         * @return The VerneGUI instance.
         */
        fun get(): VerneGui = InternalVerneGui
    }
}