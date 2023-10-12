package dev.butter.gui.internal.validation

import dev.butter.gui.api.type.GuiClass
import dev.butter.gui.internal.InternalGuiHandler.dynamicGuis
import dev.butter.gui.internal.InternalGuiHandler.staticGuis

internal fun validateStatic(gui: GuiClass) =
    check(gui in staticGuis) {
        "GUI: ${gui.simpleName} is not a static GUI."
    }

internal fun validateNonStatic(gui: GuiClass) =
    check(gui !in staticGuis) {
        "GUI: ${gui.simpleName} is not a non-static GUI."
    }

internal fun validateRegistered(gui: GuiClass) =
    check(gui in staticGuis || gui in dynamicGuis) {
        "GUI: ${gui.simpleName} is not registered."
    }