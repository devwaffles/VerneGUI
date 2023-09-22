package dev.butter.gui.internal.exception.contents

import dev.butter.gui.api.base.GUIContents
import dev.butter.gui.internal.exception.base.ContentException
import dev.butter.gui.internal.validation.slotRange

internal class ContentSlotRangeException(
    override val contents: GUIContents,
    override val inputRange: IntRange,
) : ContentException(
    "Invalid slot range entered: $inputRange in inventory owned by " +
    "${contents.gui.inventory.holder ?: "nobody (static gui)"}. Inventory slots " +
    "must be in ${contents.slotRange}."
)