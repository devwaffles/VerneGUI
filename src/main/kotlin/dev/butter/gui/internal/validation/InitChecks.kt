package dev.butter.gui.internal.validation

import dev.butter.gui.api.base.BaseGUI
import dev.butter.gui.api.type.GUIType.STATIC
import dev.butter.gui.internal.InternalGUIHandler.playerDependencies
import dev.butter.gui.internal.extensions.annotatedDependencyFields
import dev.butter.gui.internal.extensions.filterValues
import dev.butter.gui.internal.extensions.type
import kotlin.reflect.KClass

internal fun KClass<out BaseGUI>.validateDependencies() {
    if (this.type != STATIC) {
        return
    }

    this.annotatedDependencyFields()
        .filterValues(playerDependencies.keys::contains)
        .any { (field, clazz) ->
            error("Static GUI: ${this.simpleName} has a field named ${field.name} of type ${clazz.simpleName}. Player dependencies are only allowed in dynamic GUIs.")
        }
}