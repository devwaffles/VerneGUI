package dev.butter.gui.internal.extensions

import dev.butter.gui.api.annotation.PageContents
import dev.butter.gui.api.base.BaseGui
import dev.butter.gui.api.base.GuiContents
import dev.butter.gui.api.extensions.pageItems
import dev.butter.gui.api.item.builder.item
import dev.butter.gui.api.item.types.GuiItem
import dev.butter.gui.api.item.types.PageAction.NONE
import dev.butter.gui.api.item.types.PageItem
import dev.butter.gui.internal.InternalGuiHandler.dynamicDependencies
import dev.butter.gui.internal.InternalGuiHandler.injector
import dev.butter.gui.internal.InternalGuiHandler.singletons
import dev.butter.gui.internal.InternalGuiHandler.staticDependencies
import dev.butter.gui.internal.InternalGuiHandler.toInjectGuice
import dev.butter.gui.internal.validation.GuiConstants.MAX_PAGES
import org.bukkit.ChatColor.BOLD
import org.bukkit.ChatColor.translateAlternateColorCodes
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import kotlin.math.ceil
import kotlin.reflect.full.findAnnotation

internal fun BaseGui.init(
    owner: Player?,
    plugin: JavaPlugin,
) {
    this.plugin = plugin

    if (toInjectGuice()) {
        injector.injectMembers(this)
    }

    injectNonPlayerDependencies()

    if (owner != null) {
        this.owner = owner
        injectPlayerDependencies(owner)
    }

    val title = this::class.title
    this.contents = GuiContents(this)
    this.gui = plugin.server.createInventory(this, this::class.rows * 9, "${title.color}${if (title.bold) BOLD else ""}${translateAlternateColorCodes('&', title.value)}")

    this.update()
}

internal fun BaseGui.injectNonPlayerDependencies() = this::class.annotatedDependencyFields
    .filterValues((staticDependencies + singletons).keys::contains)
    .mapValues { dep -> (staticDependencies + singletons)[dep]!! }
    .forEach { (field, init) ->
        field.isAccessible = true
        field.set(this, init(plugin))
    }

internal fun BaseGui.injectPlayerDependencies(player: Player) = this::class.annotatedDependencyFields
    .filterValues(dynamicDependencies.keys::contains)
    .mapValues { dep -> dynamicDependencies[dep]!! }
    .forEach { (field, init) ->
        field.isAccessible = true
        field.set(this, init(player, plugin))
    }

internal fun BaseGui.update() {
    this.createDefaultContents()
    this.createPageContents()
    this.contents.init()
}

internal fun BaseGui.createDefaultContents() =
    this::class.defaultContentsMethod.call(this)

internal fun BaseGui.pageSlots() = this.contents.contentItems
    .filterValuesIsInstance<Int, GuiItem, PageItem>()
    .filterValues { it.pageAction == NONE }

internal fun BaseGui.createPageContents() {
    if (!this::class.hasPageContentsMethod) {
        return
    }

    val pageContentsMethod = this::class.pageContentsMethod
    val autoClear = pageContentsMethod.findAnnotation<PageContents>()!!.autoClear

    if (autoClear) {
        this.contents.pageItems.clear()
    }

    pageContentsMethod.call(this)

    updatePageCount()
    fillPageItems()
}

internal fun BaseGui.updatePageCount() {
    val pageItems = this.pageItems()
    val pageSlots = this.pageSlots()

    if (pageItems.isEmpty() ||
        pageSlots.isEmpty()
    ) {
        return
    }

    val pageItemCount = pageItems.size
    val pageSlotCount = pageSlots.size
    val pageCount = ceil(pageItemCount.toFloat() / pageSlotCount).toInt()

    check(pageCount < MAX_PAGES) {
        "GUI: ${this::class.simpleName} has too many pages, with a total of $pageItemCount page items and only $pageSlotCount page item slots creating $pageCount pages. Max pages is $MAX_PAGES."
    }

    this.pages = 1..pageCount

    if (this.current > pageCount) {
        this.current = pageCount
    }
}

internal fun BaseGui.fillPageItems() {
    val pageSlots = this.pageSlots()
    val pageItems = this.pageItems()

    if (pageSlots.isEmpty()) {
        return
    }

    val pageSlotCount = pageSlots.size
    val mappedIndex: (Int) -> Int = { index ->
        (this.current - 1) * pageSlotCount + index
    }

    pageSlots.toIndexedMap()
        .mapKeys(mappedIndex)
        .forEach { (index, pageItem) ->
            pageItem.stack = pageItems.getOrNull(index) ?: item()
        }
}