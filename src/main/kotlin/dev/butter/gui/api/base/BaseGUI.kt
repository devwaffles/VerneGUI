package dev.butter.gui.api.base

import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.plugin.java.JavaPlugin

abstract class BaseGUI : InventoryHolder {
    lateinit var plugin: JavaPlugin internal set
    lateinit var owner: Player internal set
    lateinit var contents: GUIContents internal set
    internal lateinit var gui: Inventory

    abstract fun createContents()

    override fun getInventory(): Inventory = gui
}