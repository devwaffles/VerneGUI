package dev.butter.gui.api.annotation

import org.bukkit.ChatColor

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class GUITitle(
    val value: String,
    val color: ChatColor = ChatColor.RESET,
    val bold: Boolean = false,
)
