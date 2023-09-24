package dev.butter.gui.api.item.builder

import org.bukkit.enchantments.Enchantment

/**
 * Represents an enchantment container.
 */
data class EnchantContainer(
    val enchant: Enchantment,
    val level: Int,
)