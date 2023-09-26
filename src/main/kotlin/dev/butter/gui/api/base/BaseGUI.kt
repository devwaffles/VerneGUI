package dev.butter.gui.api.base

import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.plugin.java.JavaPlugin

/**
 * The base class for all GUIs that are registered
 * with the GUI API. This class is used to create
 * custom GUIs, and is not meant to be used directly.
 * All GUIs should extend this class.
 *
 * The [createContents] method is used to create the
 * items inside the inventory. This method is called
 * after any dependencies are injected, and before
 * players open it. The [createContents] method will
 * be called automatically as a player clicks on
 * clickable items. Items created by [createContents]
 * add GUIItems to the [contents] field, which stores
 * a map of slots to GUIItems.
 *
 * The [plugin] field is used to store the plugin that
 * registered the GUI. The [owner] field corresponds
 * to the player that owns the GUI, if the GUI is
 * dynamic. It will be uninitialized if the GUI is
 * static.
 *
 * @see dev.butter.gui.api.annotation.Dependency
 * @see GUIContents
 */
abstract class BaseGUI : InventoryHolder {
    internal lateinit var gui: Inventory
    lateinit var plugin: JavaPlugin
        internal set
    lateinit var owner: Player
        internal set
    lateinit var contents: GUIContents
        internal set
    lateinit var pages: IntRange
        internal set
    var current: Int = 1
        internal set

    override fun getInventory(): Inventory = gui
}

/*
 * setup so default contents generates the gui that is constant between pages
 * setup so page contents generates the gui that is different between pages
 * default contents can only call the partition method for page specific contents
 *
 */