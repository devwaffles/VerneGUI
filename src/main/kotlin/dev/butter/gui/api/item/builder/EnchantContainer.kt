package dev.butter.gui.api.item.builder

import org.bukkit.enchantments.Enchantment

data class EnchantContainer(
    val enchant: Enchantment,
    val level: Int,
)