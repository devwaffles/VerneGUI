package dev.butter.gui.api.extensions

import dev.butter.gui.api.base.BaseGui
import org.bukkit.entity.Player
import org.bukkit.inventory.InventoryView

/**
 * Opens the specified GUI for the player.
 */
infix fun <G : BaseGui> Player.open(gui: G): InventoryView = this.openInventory(gui.inventory)