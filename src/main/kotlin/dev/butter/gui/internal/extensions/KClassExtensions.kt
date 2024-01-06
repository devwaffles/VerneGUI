package dev.butter.gui.internal.extensions

import dev.butter.gui.api.annotation.*
import dev.butter.gui.api.base.BaseGui
import java.lang.reflect.Field
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.jvm.javaField

internal val <T : BaseGui> KClass<T>.rows
    get() = this.findAnnotation<GuiSize>()!!.rows

internal val <T : BaseGui> KClass<T>.type
    get() = this.findAnnotation<TypeAlias>()!!.type

internal val <T : BaseGui> KClass<T>.title
    get() = this.findAnnotation<GuiTitle>()!!

internal val <T : BaseGui> KClass<T>.clickDelay
    get() = this.findAnnotation<ClickDelay>()?.delay ?: 1L

internal val <T : BaseGui> KClass<T>.defaultContentsMethod
    get() = this.declaredMemberFunctions
        .first { it.hasAnnotation<DefaultContents>() }

internal val <T : BaseGui> KClass<T>.pageContentsMethod
    get() = this.declaredMemberFunctions
        .first { it.hasAnnotation<PageContents>() }

internal val <T : Any> KClass<T>.noArgsConstructor
    get() = this.constructors
        .first { it.parameters.all(KParameter::isOptional) }

internal val <T : BaseGui> KClass<T>.annotatedDependencyFields
    get() = this.declaredMemberProperties
        .mapNotNull(KProperty1<T, *>::javaField)
        .filter { it.isAnnotationPresent(Dependency::class.java) }
        .associateWithNotNull(Field::getType)
        .mapValues(Class<*>::kotlin)

internal val <T : BaseGui> KClass<T>.hasDefaultContentsMethod
    get() = this.declaredMemberFunctions
        .any { it.hasAnnotation<DefaultContents>() }

internal val <T : BaseGui> KClass<T>.hasPageContentsMethod
    get() = this.declaredMemberFunctions
        .any { it.hasAnnotation<PageContents>() }

internal val <T : Any> KClass<T>.hasNoArgsConstructor
    get() = this.constructors
        .any { it.parameters.all(KParameter::isOptional) }