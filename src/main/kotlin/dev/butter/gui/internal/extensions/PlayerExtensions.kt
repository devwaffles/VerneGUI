package dev.butter.gui.internal.extensions

import dev.butter.gui.api.base.BaseGUI
import org.bukkit.entity.Player
import org.bukkit.inventory.InventoryView

infix fun <G : BaseGUI> Player.open(gui: G): InventoryView = this.openInventory(gui.inventory)