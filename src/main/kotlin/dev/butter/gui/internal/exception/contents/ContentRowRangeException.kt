package dev.butter.gui.internal.exception.contents

import dev.butter.gui.api.base.GUIContents
import dev.butter.gui.internal.exception.base.ContentException
import dev.butter.gui.internal.validation.RangeConstants.DEFAULT_ROWS

internal class ContentRowRangeException(
    override val contents: GUIContents,
    override val inputRange: IntRange,
) : ContentException(
    "Invalid row range entered: $inputRange in inventory owned by " +
    "${contents.inventory.holder ?: "nobody (static gui)"}. Inventory rows " +
    "must be between $DEFAULT_ROWS."
)

