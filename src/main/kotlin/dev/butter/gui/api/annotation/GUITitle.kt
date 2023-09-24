package dev.butter.gui.api.annotation

import org.bukkit.ChatColor

/**
 * This annotation is used to specify
 * the title of a GUI. The title must
 * be used on the GUI class. The title
 * will be displayed at the top of the
 * inventory. The title can be colored
 * and bolded. If the title is more
 * complex and utilizes multiple colors,
 * color codes can be used within the
 * string, and will be resolved.
 *
 * @see dev.butter.gui.api.VerneGUI
 * @see org.bukkit.ChatColor
 * @see org.bukkit.ChatColor.translateAlternateColorCodes
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class GUITitle(
    val value: String,
    val color: ChatColor = ChatColor.RESET,
    val bold: Boolean = false,
)
