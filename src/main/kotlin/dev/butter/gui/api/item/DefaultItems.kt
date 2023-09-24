package dev.butter.gui.api.item

import dev.butter.gui.api.item.builder.item
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

enum class DefaultItems(val item: ItemStack) {
    FILLER_PANE(
        item {
            type = Material.STAINED_GLASS_PANE
            durability = 7
            displayName = " "
        }
    ),
    WHITE_PANE(
        item {
            copyItem(FILLER_PANE.item)
            durability = 0
        }
    ),
    ORANGE_PANE(
        item {
            copyItem(FILLER_PANE.item)
            durability = 1
        }
    ),
    MAGENTA_PANE(
        item {
            copyItem(FILLER_PANE.item)
            durability = 2
        }
    ),
    LIGHT_BLUE_PANE(
        item {
            copyItem(FILLER_PANE.item)
            durability = 3
        }
    ),
    YELLOW_PANE(
        item {
            copyItem(FILLER_PANE.item)
            durability = 4
        }
    ),
    LIME_PANE(
        item {
            copyItem(FILLER_PANE.item)
            durability = 5
        }
    ),
    CYAN_PANE(
        item {
            copyItem(FILLER_PANE.item)
            durability = 9
        }
    ),
    BLUE_PANE(
        item {
            copyItem(FILLER_PANE.item)
            durability = 11
        }
    ),
    BROWN_PANE(
        item {
            copyItem(FILLER_PANE.item)
            durability = 12
        }
    ),
    GREEN_PANE(
        item {
            copyItem(FILLER_PANE.item)
            durability = 13
        }
    ),
    RED_PANE(
        item {
            copyItem(FILLER_PANE.item)
            durability = 14
        }
    ),
    BLACK_PANE(
        item {
            copyItem(FILLER_PANE.item)
            durability = 15
        }
    );

    companion object {
        fun conditionPane(condition: Boolean) = if (condition) GREEN_PANE.item else RED_PANE.item
    }
}