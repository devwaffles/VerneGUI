package dev.butter.gui.api.annotation

import dev.butter.gui.api.VerneGui
import dev.butter.gui.api.base.BaseGui
import org.bukkit.ChatColor
import org.bukkit.ChatColor.translateAlternateColorCodes

/**
 * This annotation is used to specify
 * the title of a GUI. This annotation
 * must be present on all registered
 * [BaseGui]s. The title
 * will be displayed at the top of the
 * inventory. The title can be colored
 * and bolded. If the title is more
 * complex and utilizes multiple colors,
 * color codes can be used within the
 * string, and will be resolved.
 *
 * @see BaseGui
 * @see VerneGui
 * @see ChatColor
 * @see translateAlternateColorCodes
 *
 * @param value The title of the GUI.
 * @param color The color of the title.
 * @param bold Whether the title should be bolded.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class GuiTitle(
    val value: String = "Default Title",
    val color: ChatColor = ChatColor.RESET,
    val bold: Boolean = false,
)