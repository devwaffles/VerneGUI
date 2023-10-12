package dev.butter.gui.api.base

import dev.butter.gui.api.annotation.GuiSize
import dev.butter.gui.api.annotation.GuiTitle
import dev.butter.gui.api.annotation.TypeAlias
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.plugin.java.JavaPlugin

/**
 * The base class for all GUIs that are registered
 * with the API. This class should be extended by
 * all GUIs that are registered with the API. This
 * class is used to store the GUI's inventory, the
 * plugin that registered the GUI, the player that
 * owns the GUI, the GUI's contents, the GUI's current
 * page. This class also implements [InventoryHolder]
 * to allow for the GUI's inventory to be opened, or to
 * be listened to by listeners.
 *
 * This class should not be instantiated directly.
 * On all registered GUIs, the subclass must have
 * three annotations: [TypeAlias], [GuiTitle], and
 * [GuiSize].
 *
 * @see GuiContents
 */
abstract class BaseGui : InventoryHolder {
    lateinit var gui: Inventory
        internal set
    lateinit var plugin: JavaPlugin
        internal set
    lateinit var owner: Player
        internal set
    lateinit var contents: GuiContents
        internal set
    var pages: IntRange = 0..0
        internal set
    var current: Int = 1
        internal set

    override fun getInventory(): Inventory = gui
}