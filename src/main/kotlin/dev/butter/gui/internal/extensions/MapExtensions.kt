package dev.butter.gui.internal.extensions

fun <K, V> Map<K, V?>.filterNotNullValues(): Map<K, V> =
    mapNotNull { (key, value) -> value?.let { key to it } }.toMap()

fun <K, V> Map<K, V>.filterValues(predicate: (V) -> Boolean): Map<K, V> =
    filter { (_, value) -> predicate(value) }

fun <K, V, T> Map<K, V>.mapValues(transform: (V) -> T): Map<K, T> =
    map { entry -> entry.key to transform(entry.value) }.toMap()

fun <K, V> Map<K, V>.forEachKeyAction(action: K.(V) -> Unit): Unit =
    forEach { (key, value) -> key.action(value) }

fun <K, V> Map<K, V>.onEachKey(action: (K) -> Unit): Map<K, V> =
    apply { for ((key, _) in this) action(key) }