package dev.butter.gui.internal.extensions

internal fun <T, V> Iterable<T>.associateWithNotNull(transform: (T) -> V?): Map<T, V> =
    associateWith(transform).filterNotNullValues()

internal fun <V> Map<Int, V>.toIndexedMap(): Map<Int, V> = keys
    .sorted()
    .mapIndexed(::IndexedValue)
    .associate { (index, key) -> index to getValue(key) }

internal inline fun <reified T> Iterable<*>.anyInstanceOf() =
    firstOrNull { it is T } != null