package dev.butter.gui.internal.updater

import dev.butter.gui.api.base.BaseGUI
import dev.butter.gui.api.base.GUIContents
import dev.butter.gui.api.base.update
import dev.butter.gui.api.item.types.Animated
import dev.butter.gui.api.item.types.GUIItem
import dev.butter.gui.internal.InternalGUIHandler.nonPlayerGuiInstances
import dev.butter.gui.internal.InternalGUIHandler.playerGuiInstances
import dev.butter.gui.internal.extensions.*
import dev.butter.gui.internal.updater.GUIUpdater.currentTick
import org.bukkit.inventory.ItemStack
import org.bukkit.scheduler.BukkitRunnable

internal object GUIUpdater : BukkitRunnable() {
    internal var currentTick = 0L

    override fun run() {
        updateStaticGuis()
        updateDynamicGuis()

        currentTick++
    }

    private fun updateStaticGuis() {
        nonPlayerGuiInstances
            .map(BaseGUI::contents)
            .filter(GUIContents::hasAnimatedItems)
            .map(GUIContents::constantItems)
            .flatMap(MutableMap<Int, GUIItem>::values)
            .associateWithNotNull { it as? Animated }
            .filterValues(Animated::onTick)
            .mapValues(Animated::cycleItems)
            .forEachKeyAction(GUIItem::cycleItem)

        nonPlayerGuiInstances.forEach(BaseGUI::update)
    }

    private fun updateDynamicGuis() {
        playerGuiInstances.values
            .flatten()
            .map(BaseGUI::contents)
            .filter(GUIContents::hasAnimatedItems)
            .map(GUIContents::constantItems)
            .flatMap(MutableMap<Int, GUIItem>::values)
            .associateWithNotNull { it as? Animated }
            .filterValues(Animated::onTick)
            .mapValues(Animated::cycleItems)
            .forEachKeyAction(GUIItem::cycleItem)

        playerGuiInstances.values
            .flatten()
            .forEach(BaseGUI::update)
    }
}

private fun Animated.onTick() = currentTick % tickSpeed == 0L

private fun GUIItem.cycleItem(items: List<ItemStack>) {
    val currentItem = this.item
    val index = items.indexOf(currentItem)
    this.item = items[(index + 1) % (items.size)]
}