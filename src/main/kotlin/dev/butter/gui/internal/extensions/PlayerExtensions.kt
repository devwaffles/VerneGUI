package dev.butter.gui.internal.extensions

import dev.butter.gui.api.base.BaseGUI
import dev.butter.gui.internal.InternalGUIHandler.dynamicGuiSet
import dev.butter.gui.internal.InternalGUIHandler.playerGuiInstances
import dev.butter.gui.internal.InternalGUIHandler.plugin
import dev.butter.gui.internal.types.GUIClass
import org.bukkit.entity.Player
import kotlin.reflect.full.createInstance

internal fun Player.registerPlayer() {
    playerGuiInstances += this.uniqueId to dynamicGuiSet
        .asSequence()
        .map(GUIClass::createInstance)
        .onEach(BaseGUI::injectNonPlayerDependencies)
        .onEach { gui -> gui.injectPlayerDependencies(this) }
        .onEach { gui -> gui.init(this, plugin) }
        .toSet()
}

infix fun <G : BaseGUI> Player.open(gui: G) {
    this.openInventory(gui.inventory)
}