package dev.butter.gui.api.extensions

import dev.butter.gui.api.base.BaseGui
import org.bukkit.entity.Player

infix fun BaseGui.open(player: Player) = player open this

val BaseGui.innerSlots get() = (10..<inventory.size - 10)
    .filter { it % 9 != 0 && it % 9 != 8 }

val BaseGui.outerSlots get() = (0..<inventory.size)
    .filter { it !in innerSlots }

fun BaseGui.pageItems() = this.contents.pageItems

fun BaseGui.pageItemCount() = this.pageItems().size