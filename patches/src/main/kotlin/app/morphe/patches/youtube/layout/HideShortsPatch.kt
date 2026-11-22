package app.morphe.patches.youtube.layout

import app.morphe.patcher.patch.patch
import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.patches.youtube.misc.ShortsControllerFingerprint
import app.morphe.util.returnEarly

@Suppress("unused")
val hideShortsPatch = patch(
    name = "Hide Shorts components",
    description = "Hides Shorts shelf, tab, and creation button from the UI.",
    default = false,
) {
    category("Layout")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        ShortsControllerFingerprint.method.returnEarly()
    }
}