package dev.butter.gui.internal.validation

import dev.butter.gui.internal.InternalGUIHandler.dynamicGuis
import dev.butter.gui.internal.InternalGUIHandler.staticGuis
import dev.butter.gui.api.type.GUIClass

internal fun validateStatic(gui: GUIClass) {
    check(gui in staticGuis) {
        "GUI: ${gui.simpleName} is not a static GUI."
    }
}

internal fun validateNonStatic(gui: GUIClass) {
    check(gui !in staticGuis) {
        "GUI: ${gui.simpleName} is not a non-static GUI."
    }
}

internal fun validateRegistered(gui: GUIClass) {
    check(gui in staticGuis || gui in dynamicGuis) {
        "GUI: ${gui.simpleName} is not registered."
    }
}