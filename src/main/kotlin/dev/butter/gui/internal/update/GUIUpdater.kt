package dev.butter.gui.internal.update

import dev.butter.gui.api.base.GUIContents
import dev.butter.gui.api.base.VerneBaseGUI
import dev.butter.gui.api.item.Animated
import dev.butter.gui.api.item.GUIItem
import dev.butter.gui.internal.InternalGUIHandler.nonPlayerGuiInstances
import dev.butter.gui.internal.InternalGUIHandler.playerGuiInstances
import dev.butter.gui.internal.extensions.associateWithNotNull
import dev.butter.gui.internal.extensions.forEachKeyAction
import dev.butter.gui.internal.extensions.mapValues
import dev.butter.gui.internal.extensions.update
import dev.butter.gui.internal.update.GUIUpdater.currentTick
import dev.butter.gui.internal.validation.RangeConstants.DEFAULT_DELAYS
import org.bukkit.inventory.ItemStack
import org.bukkit.scheduler.BukkitRunnable

internal object GUIUpdater : BukkitRunnable() {
    internal var currentTick = 0L

    override fun run() {
        updateStaticGuis()
        updateDynamicGuis()
    }

    private fun updateStaticGuis() {
        currentTick++

        nonPlayerGuiInstances
            .map(VerneBaseGUI::contents)
            .flatMap(GUIContents::items)
            .associateWithNotNull { it as? Animated }
            .filterValues(Animated::onTick)
            .mapValues(Animated::cycleItems)
            .forEachKeyAction(GUIItem::cycleItem)

        nonPlayerGuiInstances.forEach(VerneBaseGUI::update)

        if (currentTick == DEFAULT_DELAYS.last) {
            currentTick = 0L
        }
    }

    private fun updateDynamicGuis() {
        playerGuiInstances.values
            .flatten()
            .map(VerneBaseGUI::contents)
            .flatMap(GUIContents::items)
            .associateWithNotNull { it as? Animated }
            .filterValues(Animated::onTick)
            .mapValues(Animated::cycleItems)
            .forEachKeyAction(GUIItem::cycleItem)

        playerGuiInstances.values
            .flatten()
            .forEach(VerneBaseGUI::update)
    }
}

private fun Animated.onTick() = currentTick % tickSpeed < DEFAULT_DELAYS.first

private fun GUIItem.cycleItem(items: List<ItemStack>) {
    val currentItem = this.item
    val index = items.indexOf(currentItem)
    this.item = items[index + 1 % items.size]
}