package dev.butter.gui.api.annotation

import com.google.inject.Inject
import com.google.inject.Injector
import dev.butter.gui.api.base.BaseGui
import dev.butter.gui.api.type.DynamicInit
import dev.butter.gui.api.type.StaticInit

/**
 * Marks a field as a dependency for a [BaseGui].
 * This is used for lightweight dependency injection.
 *
 * For static GUIs, this annotation ensures that
 * fields marked with this annotation, which are
 * static (non-player specific) or singleton, are
 * injected using the [StaticInit] function
 * that was registered. This occurs before the GUI
 * calls the method marked with [DefaultContents].
 *
 * In dynamic GUIs, this annotation ensures that
 * fields marked with this annotation, which are
 * dynamic, static, or singleton, are injected using either
 * the [DynamicInit] function
 * or the [StaticInit] function that was registered.
 *
 * In addition to this annotation, Guice's `@Inject` annotation
 * is also supported. If a field is marked with the [Inject]
 * annotation, it will be injected using Guice's dependency injection
 * framework. This is useful for more complex dependencies that
 * are not supported by the lightweight dependency injection.
 * This requires that you specify an [Injector]
 * when calling the [dev.butter.gui.api.VerneGui.init] function on the VerneGui API.
 * This injector will be used to inject the fields marked with
 * the [Inject] annotation.
 *
 * This lightweight dependency injection only supports
 * field injection. Constructor injection is not supported.
 *
 * @see DynamicInit
 * @see StaticInit
 * @see BaseGui
 * @see DefaultContents
 * @see dev.butter.gui.api.VerneGui.registerStatic
 * @see dev.butter.gui.api.VerneGui.registerDynamic
 * @see dev.butter.gui.api.VerneGui.registerSingleton
 * @see dev.butter.gui.api.VerneGui.init
 * @see Inject
 * @see Injector
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class Dependency