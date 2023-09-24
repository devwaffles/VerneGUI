package dev.butter.gui.internal.extensions

fun <T, V> Iterable<T>.associateWithNotNull(transform: (T) -> V?): Map<T, V> =
    associateWith(transform).filterNotNullValues()

inline fun <reified T> Iterable<*>.anyInstanceOf() =
    firstOrNull { it is T } != null