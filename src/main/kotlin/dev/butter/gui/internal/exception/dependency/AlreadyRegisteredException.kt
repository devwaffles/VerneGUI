package dev.butter.gui.internal.exception.dependency

import dev.butter.gui.internal.exception.base.DependencyException
import dev.butter.gui.internal.types.AnyClass

internal class AlreadyRegisteredException(
    override val dependency: AnyClass
) : DependencyException(
    "Dependency: ${dependency.simpleName} has already been registered."
)