package dev.butter.gui.internal.extensions

internal fun <T, V> Iterable<T>.associateWithNotNull(transform: (T) -> V?): Map<T, V> =
    associateWith(transform).filterNotNullValues()

internal inline fun <reified T> MutableIterable<*>.removeIfInstance() =
    this.removeAll { it is T }