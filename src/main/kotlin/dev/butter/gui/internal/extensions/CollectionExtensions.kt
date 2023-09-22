package dev.butter.gui.internal.extensions

internal fun <T, V> Iterable<T>.associateWithNotNull(transform: (T) -> V?): Map<T, V> =
    associateWith(transform).filterNotNullValues()

internal fun <T, C : MutableCollection<T>, F : T> C.removeIfInstance(clazz: Class<F>) =
    removeIf(clazz::isInstance)