package dev.butter.gui.internal.extensions

fun <K, V> Map<K, V?>.filterNotNullValues(): Map<K, V> =
    mapNotNull { (key, value) -> value?.let { key to it } }.toMap()

fun <K, V> Map<K, V>.filterValues(predicate: (V) -> Boolean): Map<K, V> =
    filter { (_, value) -> predicate(value) }

inline fun <K, V, reified T : V> Map<K, V>.filterValuesIsInstance(): Map<K, T> =
    mapValuesNotNull { value ->
        if (value is T) value else null
    }

fun <K, V, T> Map<K, V>.mapKeys(transform: (K) -> T): Map<T, V> =
    map { entry -> transform(entry.key) to entry.value }.toMap()

fun <K, V, T> Map<K, V>.mapKeysNotNull(transform: (K) -> T?): Map<T, V> =
    mapNotNull { entry -> transform(entry.key)?.let { it to entry.value } }.toMap()

fun <K, V, T> Map<K, V>.mapValues(transform: (V) -> T): Map<K, T> =
    map { entry -> entry.key to transform(entry.value) }.toMap()

fun <K, V, T> Map<K, V>.mapValuesNotNull(transform: (V) -> T?): Map<K, T> =
    mapNotNull { entry -> transform(entry.value)?.let { entry.key to it } }.toMap()