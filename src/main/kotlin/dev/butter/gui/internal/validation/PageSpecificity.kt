package dev.butter.gui.internal.validation

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FUNCTION)
internal annotation class NonPageSpecific

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FUNCTION)
internal annotation class PageSpecific