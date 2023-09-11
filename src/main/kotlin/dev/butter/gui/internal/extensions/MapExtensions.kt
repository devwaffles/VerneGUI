package dev.butter.gui.internal.extensions

internal fun <K, V> Map<K, V?>.filterNotNullValues(): Map<K, V> =
    mapNotNull { (key, value) -> value?.let { key to it } }.toMap()

internal fun <K, V> Map<K, V>.filterValues(predicate: (V) -> Boolean): Map<K, V> =
    filter { (_, value) -> predicate(value) }

internal fun <K, V, T> Map<K, V>.mapValues(transform: (V) -> T): Map<K, T> =
    map { entry -> entry.key to transform(entry.value) }.toMap()

internal fun <K, V> Map<K, V>.onEachKey(action: K.(V) -> Unit): Map<K, V> =
    apply { for ((key, value) in this) key.action(value) }