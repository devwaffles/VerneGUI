package dev.butter.gui.api.item.builder

import org.bukkit.enchantments.Enchantment

/**
 * Represents an enchantment container.
 */
data class EnchantContainer(
    val enchant: Enchantment,
    val level: Int,
)

/**
 * Creates an enchantment container.
 */
fun enchantOf(
    enchant: Enchantment,
    level: Int,
) = EnchantContainer(enchant, level)