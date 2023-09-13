package dev.butter.gui.internal.update

import dev.butter.gui.internal.InternalGUIHandler
import dev.butter.gui.internal.InternalGUIHandler.nonPlayerGuiInstances
import org.bukkit.scheduler.BukkitRunnable

internal class GUIUpdater : BukkitRunnable() {
    override fun run() {
        updateStaticGuis()
    }

    private fun updateStaticGuis() {
        nonPlayerGuiInstances.forEach { gui ->
            gui.contents
        }
    }
}