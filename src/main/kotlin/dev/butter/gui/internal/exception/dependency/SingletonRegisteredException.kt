package dev.butter.gui.internal.exception.dependency

import dev.butter.gui.internal.AnyClass
import dev.butter.gui.internal.exception.base.DependencyException

internal class SingletonRegisteredException(
    override val dependency: AnyClass,
    singleton: Boolean,
) : DependencyException(
    "Dependency: ${dependency.simpleName} has already been registered " +
    if (singleton) "as a singleton. " else "not as a singleton. " +
    "Dependencies cannot be duplicate registered under singleton and non-singleton."
)
