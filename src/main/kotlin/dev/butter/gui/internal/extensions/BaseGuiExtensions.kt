package dev.butter.gui.internal.extensions

import dev.butter.gui.api.annotation.PageContents
import dev.butter.gui.api.base.BaseGui
import dev.butter.gui.api.base.GuiContents
import dev.butter.gui.api.extensions.pageItems
import dev.butter.gui.api.item.builder.item
import dev.butter.gui.api.item.types.GuiItem
import dev.butter.gui.api.item.types.PageAction.NONE
import dev.butter.gui.api.item.types.PageFilter
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
import org.bukkit.inventory.ItemStack
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

fun BaseGui.update() {
    this.createDefaultContents()
    this.createPageContents()
    this.contents.init()
}

internal fun BaseGui.createDefaultContents() {
    this.contents.contentItems.clear()

    this::class.defaultContentsMethod.call(this)
}

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

    if (pageItems.isEmpty() || pageSlots.isEmpty()) {
        return
    }

    val (filteredItems, filterSlotCount, filterPageCount, _) = this.getFilterData()
    val highestPageFilter = filterPageCount
        .maxByOrNull(Map.Entry<PageFilter, Int>::value) ?: return
    val highestPageCount = highestPageFilter.value
    val slotCount = filterSlotCount[highestPageFilter.key] ?: return
    val itemCount = filteredItems[highestPageFilter.key]?.size ?: return

    check(highestPageCount < MAX_PAGES) {
        "GUI: ${this::class.simpleName} has too many pages, with one slot filter containing $itemCount page items and only $slotCount page item slots creating $highestPageCount pages. Max pages is $MAX_PAGES."
    }

    this.pages = 1..highestPageCount

    if (this.current > highestPageCount) {
        this.current = highestPageCount
    }
}

internal fun BaseGui.fillPageItems() {
    val pageSlots = this.pageSlots()

    if (pageSlots.isEmpty()) {
        return
    }

    val (filteredItems, filterSlotCount, filterPageCount, filterItemIndexCount) = this.getFilterData()

    pageSlots.values.forEach { pageItem ->
        val filter = pageItem.filter
        val filteredPageItems = filteredItems[filter] ?: return@forEach
        val slotCount = filterSlotCount[filter] ?: return@forEach
        val pageCount = filterPageCount[filter] ?: return@forEach
        val index = filterItemIndexCount[filter] ?: return@forEach
        val filteredIndex = (this.current.coerceAtMost(pageCount) - 1) * slotCount + index

        filterItemIndexCount[filter] = index + 1

        pageItem.stack = filteredPageItems.getOrNull(filteredIndex) ?: item()
    }
}

internal fun BaseGui.getUniqueFilters() = this.pageSlots()
    .values
    .map(PageItem::filter)
    .toSet()

internal fun BaseGui.getFilterData():
    Quad<
        Map<PageFilter, List<ItemStack>>,
        Map<PageFilter, Int>,
        Map<PageFilter, Int>,
        MutableMap<PageFilter, Int>
    > {
    val pageSlots = this.pageSlots()
    val pageItems = this.pageItems()
    val filters = this.getUniqueFilters()
    val filteredItems = filters
        .associateWith(pageItems::filter)
    val filterSlotCount = filters
        .associateWith { filter ->
            pageSlots.count { it.value.filter == filter }
        }
    val filterPageCount = filters
        .associateWith { filter ->
            ceil(filteredItems[filter]!!.size.toFloat() / filterSlotCount[filter]!!).toInt()
        }
    val filterItemIndexCount = filters
        .associateWith { 0 }
        .toMutableMap()

    return quadOf(filteredItems, filterSlotCount, filterPageCount, filterItemIndexCount)
}