package dev.butter.gui.api.type

import dev.butter.gui.api.annotation.TypeAlias

/**
 * Represents the GUI type.
 *
 * Use inside the TypeAlias annotation to
 * specify the type of the GUI, either static or dynamic.
 *
 * Static GUIs are GUIs that are created once and are
 * shared across all players. Dynamic GUIs are GUIs that
 * are created for each player.
 *
 * @see TypeAlias
 */
enum class GuiType {
    STATIC, DYNAMIC;
}