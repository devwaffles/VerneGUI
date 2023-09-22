package dev.butter.gui.internal.exception.dependency

import dev.butter.gui.internal.exception.base.DependencyException
import dev.butter.gui.internal.types.AnyClass
import dev.butter.gui.internal.types.GUIClass

internal class PlayerDependencyInStaticGUIException(
    override val dependency: AnyClass,
    gui: GUIClass,
    fieldName: String,
) : DependencyException(
    "Dependency: ${dependency.simpleName} is an invalid dependency found in GUI Class: ${gui.simpleName}, " +
    "the annotated field $fieldName is a player dependency that should not be in a static GUI class."
)