package dev.butter.gui.api.item.builder

import org.bukkit.Material
import org.bukkit.Material.AIR
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack

/**
 * A builder for [ItemStack]s.
 *
 * @property item Stores the ItemStack.
 * @property enchants Stores the enchants to add.
 * @property loreBuilder Stores the lore to add.
 *
 * @property type The [Material] of the [ItemStack].
 * @property displayName The display name of the [ItemStack].
 * @property durability The durability of the [ItemStack].
 * @property amount The amount of the [ItemStack].
 * @property lore The lore of the [ItemStack].
 */
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
            if (type == AIR) {
                return
            }

            this.item.itemMeta = this.item.itemMeta.apply { lore = value }
        }

    fun enchant(enchant: Enchantment, level: Int) = enchants.add(enchantOf(enchant, level))

    fun enchant(container: EnchantContainer) = enchants.add(container)

    fun lore(vararg lines: String) = lines.forEach(loreBuilder::add)

    fun copyItem(itemToCopy: ItemStack) {
        this.item = itemToCopy.clone()
    }

    fun build() = item.apply {
        for (enchant in enchants) {
            addUnsafeEnchantment(enchant.enchant, enchant.level)
        }

        this@ItemBuilder.lore = loreBuilder
    }
}

/**
 * Access the [ItemBuilder] DSL to create an [ItemStack].
 */
fun item(init: ItemBuilder.() -> Unit = {}) = ItemBuilder().apply(init).build()