package dev.butter.gui.api.annotation

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class GUISize(
    val rows: Int
)
