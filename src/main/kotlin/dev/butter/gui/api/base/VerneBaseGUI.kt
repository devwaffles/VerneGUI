package dev.butter.gui.api.base

import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

abstract class VerneBaseGUI : InventoryHolder {
    internal lateinit var gui: Inventory
    lateinit var contents: GUIContents
    internal var owner: Player? = null

    abstract fun createContents()

    override fun getInventory(): Inventory = gui
}