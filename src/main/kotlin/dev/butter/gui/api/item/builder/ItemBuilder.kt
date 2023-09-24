package dev.butter.gui.api.item.builder

import org.bukkit.Material
import org.bukkit.Material.AIR
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack

data class ItemBuilder(var item: ItemStack = ItemStack(AIR)) {
    private val enchants: MutableList<EnchantContainer> = mutableListOf()
    private val loreBuilder: MutableList<String> = mutableListOf()

    var type: Material
        get() = item.type
        set(value) { item.type = value }
    var displayName: String
        get() = this.item.itemMeta.displayName ?: ""
        set(value) {
            this.item.itemMeta = this.item.itemMeta.apply { displayName = value }
        }
    var durability: Int
        get() = item.durability.toInt()
        set(value) { item.durability = value.toShort() }
    var amount: Int
        get() = item.amount
        set(value) { item.amount = value }
    var lore: MutableList<String>
        get() = this.item.itemMeta.lore ?: mutableListOf()
        set(value) {
            this.item.itemMeta = this.item.itemMeta.apply { lore = value }
        }

    fun enchant(enchant: Enchantment, level: Int) = enchants.add(EnchantContainer(enchant, level))

    fun lore(vararg lines: String) = lines.forEach(loreBuilder::add)

    fun copyItem(itemToCopy: ItemStack) {
        this.item = itemToCopy.clone()
    }

    fun build() = item.apply {
        for (enchant in enchants) {
            addUnsafeEnchantment(enchant.enchant, enchant.level)
        }

        lore = loreBuilder
    }
}

fun item(init: ItemBuilder.() -> Unit) = ItemBuilder().apply(init).build()