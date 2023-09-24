package dev.butter.gui.internal.extensions

import dev.butter.gui.api.annotation.*
import dev.butter.gui.api.base.BaseGUI
import java.lang.reflect.Field
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.javaField

internal val <T : BaseGUI> KClass<T>.rows
    get() = this.findAnnotation<GUISize>()!!.rows

internal val <T : BaseGUI> KClass<T>.type
    get() = this.findAnnotation<TypeAlias>()!!.type

internal val <T : BaseGUI> KClass<T>.title
    get() = this.findAnnotation<GUITitle>()!!

internal val <T : BaseGUI> KClass<T>.clickDelay
    get() = this.findAnnotation<ClickDelay>()?.delay ?: 1L

internal val <T : Any> KClass<T>.hasNoArgsConstructor
    get() = this.constructors.any { it.parameters.all(KParameter::isOptional) }

internal fun <T : Any> KClass<T>.noArgsConstructor(): KFunction<T>? =
    this.constructors.singleOrNull { it.parameters.all(KParameter::isOptional) }

internal fun <T : BaseGUI> KClass<T>.annotatedDependencyFields() = this.declaredMemberProperties
    .mapNotNull(KProperty1<T, *>::javaField)
    .filter { it.isAnnotationPresent(Dependency::class.java) }
    .associateWithNotNull(Field::getType)
    .mapValues(Class<*>::kotlin)