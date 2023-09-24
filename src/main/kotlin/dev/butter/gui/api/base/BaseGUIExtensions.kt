package dev.butter.gui.api.base

import dev.butter.gui.internal.extensions.init
import dev.butter.gui.internal.extensions.open
import org.bukkit.entity.Player

infix fun BaseGUI.open(player: Player) = player open this

fun BaseGUI.update() {
    this.createContents()
    this.contents.init()
}

val BaseGUI.innerSlots get() = (10..<inventory.size - 10)
    .filter { it % 9 != 0 && it % 9 != 8 }

val BaseGUI.outerSlots get() = (0..<inventory.size)
    .filter { it !in innerSlots }