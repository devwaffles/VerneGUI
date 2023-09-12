package dev.butter.gui.internal.exception.dependency

import dev.butter.gui.internal.AnyClass
import dev.butter.gui.internal.exception.base.DependencyException

internal class MissingNoArgsConstructorException(
    override val dependency: AnyClass
) : DependencyException(
    "Dependency: ${dependency.simpleName} does not have any no-args constructors. " +
    "Register this dependency by providing a handler for its creation."
)