package dev.butter.gui.internal.extensions

import dev.butter.gui.api.base.BaseGui
import dev.butter.gui.api.base.GuiContents
import dev.butter.gui.api.extensions.open
import dev.butter.gui.api.item.types.Clickable
import dev.butter.gui.api.item.types.DefaultPageClickAction
import dev.butter.gui.api.item.types.LinkingItem
import dev.butter.gui.api.item.types.PageAction.*
import dev.butter.gui.api.item.types.PageItem
import dev.butter.gui.api.type.GuiType.DYNAMIC
import dev.butter.gui.api.type.GuiType.STATIC
import dev.butter.gui.internal.InternalGuiHandler.get
import dev.butter.gui.internal.InternalGuiHandler.getStatic
import org.bukkit.Material.AIR
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import kotlin.math.max
import kotlin.math.min

internal fun GuiContents.init() =
    this.contentItems.forEach { (slot, guiItem) ->
        this.gui.inventory.setItem(slot, guiItem.stack)
    }

internal fun GuiContents.handle(
    player: Player,
    slot: Int,
    clickType: ClickType,
    gui: BaseGui,
) {
    val item = contentItems[slot] ?: return

    if (item.stack.type == AIR) {
        return
    }

    return when (item) {
        is LinkingItem ->
            item.handle(player)

        is PageItem ->
            item.handle(player, clickType, gui)

        is Clickable -> {
            item.action(player, clickType)
            gui.update()
        }

        else -> Unit
    }
}

private fun LinkingItem.handle(player: Player) {
    val linkingGui = this.linkingGui
    val type = linkingGui.type
    val action = this.action

    when (type) {
        STATIC ->
            player open getStatic(linkingGui)
        DYNAMIC ->
            player open get(linkingGui, player)
    }

    action(player)
}

private fun PageItem.handle(
    player: Player,
    clickType: ClickType,
    gui: BaseGui,
) {
    val pageAction = this.pageAction
    val action = this.action

    when (pageAction) {
        REFRESH_PAGE ->
            return gui.update()

        NONE -> {
            if (action == DefaultPageClickAction) {
                return
            }

            action(player, clickType, this.stack)

            return gui.update()
        }

        FIRST_PAGE ->
            gui.current = gui.pages.first

        LAST_PAGE ->
            gui.current = gui.pages.last

        MIDDLE_PAGE ->
            gui.current = gui.pages.first + ((gui.pages.last - gui.pages.first) / 2)

        NEXT_PAGE ->
            gui.current = min(gui.current + 1, gui.pages.last)

        PREVIOUS_PAGE ->
            gui.current = max(gui.current - 1, gui.pages.first)
    }

    gui.fillPageItems()
}