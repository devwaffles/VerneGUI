package dev.butter.gui.internal.validation

import dev.butter.gui.api.annotation.ClickDelay
import dev.butter.gui.api.annotation.GuiSize
import dev.butter.gui.api.annotation.GuiTitle
import dev.butter.gui.api.annotation.TypeAlias
import dev.butter.gui.api.type.AnyClass
import dev.butter.gui.api.type.GuiClass
import dev.butter.gui.internal.InternalGuiHandler.dynamicDependencies
import dev.butter.gui.internal.InternalGuiHandler.isInitialized
import dev.butter.gui.internal.InternalGuiHandler.singletons
import dev.butter.gui.internal.InternalGuiHandler.staticDependencies
import dev.butter.gui.internal.extensions.clickDelay
import dev.butter.gui.internal.extensions.hasDefaultContentsMethod
import dev.butter.gui.internal.extensions.hasNoArgsConstructor
import dev.butter.gui.internal.extensions.rows
import dev.butter.gui.internal.validation.DependencyType.*
import dev.butter.gui.internal.validation.GuiConstants.CLICK_DELAYS
import kotlin.reflect.full.hasAnnotation

internal enum class DependencyType(val title: String) {
    STATIC("Static"),
    DYNAMIC("Dynamic"),
    SINGLETON("Singleton"),
}

internal fun validateUninitialized() =
    check(!isInitialized()) {
        "VerneGUI is already initialized. Register before calling init()."
    }

internal fun validate(
    type: DependencyType,
    dependency: AnyClass,
    noArgsCheck: Boolean,
) {
    if (noArgsCheck) {
        checkNoArgsConstructor(dependency)
    }

    checkDependencyMap(type, dependency)
    checkPlayerDependencyMap(type, dependency)
    checkSingletonMap(type, dependency)
}

internal fun GuiClass.validate() {
    checkNoArgsConstructor(this)

    check(this.hasAnnotation<TypeAlias>()) {
        "GUI: ${this.simpleName} does not have the annotation TypeAlias and will not be registered."
    }

    check(this.hasAnnotation<GuiTitle>()) {
        "GUI: ${this.simpleName} does not have the annotation GUITitle and will not be registered."
    }

    check(this.hasAnnotation<GuiSize>()) {
        "GUI: ${this.simpleName} does not have the annotation GUISize and will not be registered."
    }

    check(this.rows in 3..6) {
        "GUI: ${this.simpleName} has an invalid row size (${this.rows}) and will not be registered, must be in ${3..6}."
    }

    if (this.hasAnnotation<ClickDelay>()) {
        check(this.clickDelay in CLICK_DELAYS) {
            "GUI: ${this.simpleName} has an invalid click delay (${this.clickDelay}) and will not be registered, must be in $CLICK_DELAYS."
        }
    }

    check(this.hasDefaultContentsMethod) {
        "GUI: ${this.simpleName} does not have a method annotated with DefaultContents and will not be registered."
    }
}

private fun checkDependencyMap(
    type: DependencyType,
    clazz: AnyClass,
) = check(clazz !in staticDependencies.keys) {
        "${type.title}: ${clazz.simpleName} is already registered${
            when (type) {
                STATIC -> ""
                DYNAMIC -> " as a dynamic dependency"
                SINGLETON -> " as a singleton"
            }
        }."
    }

private fun checkPlayerDependencyMap(
    type: DependencyType,
    clazz: AnyClass,
) = check(clazz !in dynamicDependencies.keys) {
        "${type.title}: ${clazz.simpleName} is already registered${
            when (type) {
                STATIC -> " as a static dependency"
                DYNAMIC -> ""
                SINGLETON -> " as a singleton"
            }
        }."
    }

private fun checkSingletonMap(
    type: DependencyType,
    clazz: AnyClass,
) = check(clazz !in singletons.keys) {
        "${type.title}: ${clazz.simpleName} is already registered${
            when (type) {
                SINGLETON -> ""
                else -> " as a non singleton dependency"
            }
        }."
    }

private fun checkNoArgsConstructor(clazz: AnyClass) =
    check(clazz.hasNoArgsConstructor) {
        "Class: ${clazz.simpleName} does not have a no-args-constructor."
    }