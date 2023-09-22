package dev.butter.gui.api.base

import dev.butter.gui.internal.extensions.open
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

abstract class BaseGUI : InventoryHolder {
    internal lateinit var gui: Inventory
    internal var owner: Player? = null
    lateinit var contents: GUIContents

    abstract fun createContents()

    override fun getInventory(): Inventory = gui

    infix fun open(player: Player) = player open this
}