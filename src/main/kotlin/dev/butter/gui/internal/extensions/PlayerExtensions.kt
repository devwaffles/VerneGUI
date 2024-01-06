package dev.butter.gui.internal.extensions

import dev.butter.gui.api.type.GuiClass
import dev.butter.gui.internal.InternalGuiHandler.dynamicGuiInstances
import dev.butter.gui.internal.InternalGuiHandler.dynamicGuis
import dev.butter.gui.internal.InternalGuiHandler.plugin
import org.bukkit.entity.Player
import kotlin.reflect.full.createInstance

internal fun Player.registerPlayer() {
    dynamicGuiInstances += this.uniqueId to dynamicGuis
        .map(GuiClass::createInstance)
        .onEach { gui -> gui.init(this, plugin) }
        .toSet()
}