package dev.butter.gui.internal.validation

import dev.butter.gui.api.annotation.ClickDelay
import dev.butter.gui.api.annotation.GUISize
import dev.butter.gui.api.annotation.GUITitle
import dev.butter.gui.api.annotation.TypeAlias
import dev.butter.gui.internal.InternalGUIHandler
import dev.butter.gui.internal.InternalGUIHandler.nonPlayerDependencies
import dev.butter.gui.internal.InternalGUIHandler.playerDependencies
import dev.butter.gui.internal.InternalGUIHandler.singletons
import dev.butter.gui.internal.extensions.clickDelay
import dev.butter.gui.internal.extensions.hasNoArgsConstructor
import dev.butter.gui.internal.extensions.rows
import dev.butter.gui.internal.types.AnyClass
import dev.butter.gui.internal.types.GUIClass
import dev.butter.gui.internal.validation.DependencyType.*
import dev.butter.gui.internal.validation.RangeConstants.CLICK_DELAYS
import dev.butter.gui.internal.validation.RangeConstants.DEFAULT_ROWS
import kotlin.reflect.full.hasAnnotation

internal enum class DependencyType(val title: String) {
    DEPENDENCY("Dependency"),
    PLAYER_DEPENDENCY("Player dependency"),
    SINGLETON("Singleton"),
}

internal fun validateUninitialized() {
    check(!InternalGUIHandler.isInitialized) {
        "VerneGUI is already initialized. Register before calling init()."
    }
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

internal fun validate(gui: GUIClass) {
    checkNoArgsConstructor(gui)

    check(gui.hasAnnotation<TypeAlias>()) {
        "GUI: ${gui.simpleName} does not have the annotation TypeAlias and will not be registered."
    }

    check(gui.hasAnnotation<GUITitle>()) {
        "GUI: ${gui.simpleName} does not have the annotation GUITitle and will not be registered."
    }

    check(gui.hasAnnotation<GUISize>()) {
        "GUI: ${gui.simpleName} does not have the annotation GUISize and will not be registered."
    }

    check(gui.rows in DEFAULT_ROWS) {
        "GUI: ${gui.simpleName} has an invalid row size (${gui.rows}) and will not be registered, must be in $DEFAULT_ROWS."
    }

    if (gui.hasAnnotation<ClickDelay>()) {
        check(gui.clickDelay in CLICK_DELAYS) {
            "GUI: ${gui.simpleName} has an invalid click delay (${gui.clickDelay}) and will not be registered, must be in $CLICK_DELAYS."
        }
    }
}

internal fun checkDependencyMap(
    type: DependencyType,
    clazz: AnyClass,
) {
    check(clazz !in nonPlayerDependencies.keys) {
        "${type.title}: ${clazz.simpleName} is already registered${
            when (type) {
                DEPENDENCY -> ""
                PLAYER_DEPENDENCY -> " as a player dependency"
                SINGLETON -> " as a singleton"
            }
        }."
    }
}

internal fun checkPlayerDependencyMap(
    type: DependencyType,
    clazz: AnyClass,
) {
    check(clazz !in playerDependencies.keys) {
        "${type.title}: ${clazz.simpleName} is already registered${
            when (type) {
                DEPENDENCY -> " as a non player dependency"
                PLAYER_DEPENDENCY -> ""
                SINGLETON -> " as a singleton"
            }
        }."
    }
}

internal fun checkSingletonMap(
    type: DependencyType,
    clazz: AnyClass,
) {
    check(clazz !in singletons.keys) {
        "${type.title}: ${clazz.simpleName} is already registered${
            when (type) {
                DEPENDENCY -> " as a non singleton dependency"
                PLAYER_DEPENDENCY -> " as a non singleton player dependency"
                SINGLETON -> ""
            }
        }."
    }
}

internal fun checkNoArgsConstructor(clazz: AnyClass) {
    check(clazz.hasNoArgsConstructor) {
        "Class: ${clazz.simpleName} does not have a no-args-constructor."
    }
}