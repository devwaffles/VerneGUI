package dev.butter.gui.internal.exception.dependency

import dev.butter.gui.internal.AnyClass
import dev.butter.gui.internal.GUIClass
import dev.butter.gui.internal.exception.base.DependencyException

internal class PlayerDependencyInStaticGUIException(
    override val dependency: AnyClass,
    gui: GUIClass,
    fieldName: String,
) : DependencyException(
    "Dependency: ${dependency.simpleName} is an invalid dependency found in GUI Class: ${gui.simpleName}, " +
    "the annotated field $fieldName is a player dependency that should not be in a static GUI class."
)