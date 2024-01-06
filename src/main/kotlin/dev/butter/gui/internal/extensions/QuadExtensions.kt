package dev.butter.gui.internal.extensions

internal data class Quad<out A, out B, out C, out D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
)

internal fun <A, B, C, D> quadOf(a: A, b: B, c: C, d: D) = Quad(a, b, c, d)