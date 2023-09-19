package dev.butter.gui.internal.extensions

import dev.butter.gui.api.annotation.Dependency
import dev.butter.gui.api.base.VerneBaseGUI
import java.lang.reflect.Field
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter

internal val <T : Any> KClass<T>.hasNoArgsConstructor
    get() = this.constructors.any { it.parameters.all(KParameter::isOptional) }

internal fun <T : Any> KClass<T>.noArgsConstructor(): KFunction<T>? =
    this.constructors.singleOrNull { it.parameters.all(KParameter::isOptional) }

internal fun <T : VerneBaseGUI> KClass<T>.annotatedDependencyFields(): Map<Field, KClass<out Any>> =
    this.java.fields
        .filterNotNull()
        .filter { it.isAnnotationPresent(Dependency::class.java) }
        .associateWith { it.type.kotlin }