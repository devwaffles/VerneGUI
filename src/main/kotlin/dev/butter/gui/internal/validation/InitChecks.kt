package dev.butter.gui.internal.validation

import dev.butter.gui.api.base.BaseGui
import dev.butter.gui.api.type.GuiType.STATIC
import dev.butter.gui.internal.InternalGuiHandler.dynamicDependencies
import dev.butter.gui.internal.extensions.annotatedDependencyFields
import dev.butter.gui.internal.extensions.filterValues
import dev.butter.gui.internal.extensions.type
import kotlin.reflect.KClass

internal fun KClass<out BaseGui>.validateDependencies() {
    if (this.type != STATIC) {
        return
    }

    this.annotatedDependencyFields
        .filterValues(dynamicDependencies.keys::contains)
        .any { (field, clazz) ->
            error("Static GUI: ${this.simpleName} has a field named ${field.name} of type ${clazz.simpleName}. Player dependencies are only allowed in dynamic GUIs.")
        }
}