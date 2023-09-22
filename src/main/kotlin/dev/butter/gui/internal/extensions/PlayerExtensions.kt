package dev.butter.gui.internal.extensions

import dev.butter.gui.api.base.VerneBaseGUI
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
        .onEach(VerneBaseGUI::injectNonPlayerDependencies)
        .onEach { gui -> gui.injectPlayerDependencies(this) }
        .onEach { gui -> gui.init(this, plugin) }
        .toSet()
}