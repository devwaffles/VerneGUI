package dev.butter.gui.internal.exception.dependency

import dev.butter.gui.internal.AnyClass
import dev.butter.gui.internal.exception.base.DependencyException

internal class AlreadyRegisteredException(
    override val dependency: AnyClass
) : DependencyException(
    "Dependency: ${dependency.simpleName} has already been registered."
)