package dev.butter.gui.internal.extensions

internal fun <T, V> Iterable<T>.associateWithNotNull(transform: (T) -> V?): Map<T, V> =
    associateWith(transform).filterNotNullValues()

internal inline fun <reified K> Iterable<*>.hasInstance() =
    firstOrNull { it is K } != null