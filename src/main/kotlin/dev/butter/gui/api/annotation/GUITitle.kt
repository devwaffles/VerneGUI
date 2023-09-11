package dev.butter.gui.api.annotation

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class GUITitle(
    val value: String
)
