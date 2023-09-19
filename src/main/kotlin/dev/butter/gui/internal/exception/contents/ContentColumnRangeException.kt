package dev.butter.gui.internal.exception.contents

import dev.butter.gui.api.base.GUIContents
import dev.butter.gui.internal.exception.base.ContentException
import dev.butter.gui.internal.validation.RangeConstants.DEFAULT_COLUMNS

internal class ContentColumnRangeException(
    override val contents: GUIContents,
    override val inputRange: IntRange,
) : ContentException(
    "Invalid column range entered: $inputRange in inventory owned by ${contents.inventory.holder ?: "nobody (static gui)"}. Inventory columns must be between $DEFAULT_COLUMNS."
)