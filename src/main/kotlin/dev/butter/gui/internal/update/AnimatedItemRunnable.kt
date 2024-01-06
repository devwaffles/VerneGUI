package dev.butter.gui.internal.update

import dev.butter.gui.api.base.BaseGui
import dev.butter.gui.api.base.GuiContents
import dev.butter.gui.api.item.types.Animated
import dev.butter.gui.api.item.types.GuiItem
import dev.butter.gui.api.type.GuiClass
import dev.butter.gui.internal.InternalGuiHandler.dynamicGuiInstances
import dev.butter.gui.internal.InternalGuiHandler.staticGuiInstances
import dev.butter.gui.internal.extensions.*
import dev.butter.gui.internal.update.AnimatedItemRunnable.animatedItemGuis
import dev.butter.gui.internal.update.AnimatedItemRunnable.currentTick
import org.bukkit.scheduler.BukkitRunnable

internal object AnimatedItemRunnable : BukkitRunnable() {
    lateinit var animatedItemGuis: Set<GuiClass>
    var currentTick = 0L

    fun init() {
        val filteredStaticGuis = staticGuiInstances
            .filter(BaseGui::hasAnimatedItems)
            .map { it::class }
            .toSet()

        val filteredDynamicGuis = dynamicGuiInstances
            .values
            .flatten()
            .filter(BaseGui::hasAnimatedItems)
            .map { it::class }
            .toSet()

        animatedItemGuis = filteredStaticGuis + filteredDynamicGuis
    }

    override fun run() {
        updateStaticGuis()
        updateDynamicGuis()

        currentTick++
    }

    private fun updateStaticGuis() {
        staticGuiInstances.tickAnimatedItems()
        staticGuiInstances.updateContents()
    }

    private fun updateDynamicGuis() {
        val guis = dynamicGuiInstances.values.flatten()

        guis.tickAnimatedItems()
        guis.updateContents()
    }
}

private fun BaseGui.hasAnimatedItems() = this
    .contents
    .contentItems
    .values
    .anyInstanceOf<Animated>()

private fun Collection<BaseGui>.tickAnimatedItems() = this
    .asSequence()
    .filter(BaseGui::isAnimated)
    .map(BaseGui::contents)
    .map(GuiContents::contentItems)
    .flatMap(MutableMap<Int, GuiItem>::values)
    .mapNotNull { if (it is Animated) it else null }
    .filter(Animated::onTick)
    .forEach(GuiItem::cycleItem)

private fun BaseGui.isAnimated() = this::class in animatedItemGuis

private fun Collection<BaseGui>.updateContents() = this
    .map(BaseGui::contents)
    .forEach(GuiContents::init)

private fun Animated.onTick() = currentTick % tickSpeed == 0L

private fun <T : GuiItem> T.cycleItem() {
    val items = (this as? Animated ?: return).cycleItems
    val currentItem = this.stack
    val index = items.indexOf(currentItem)
    val nextIndex = (index + 1) % items.size

    this.stack = items[nextIndex]
}