package dev.butter.gui.api.annotation

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class ClickDelay(val delay: Long)
