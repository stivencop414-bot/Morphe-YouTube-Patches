package app.morphe.patches.youtube.misc

import app.morphe.patcher.patch.patch
import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.patches.youtube.misc.PlaybackSpeedControllerFingerprint
import app.morphe.util.returnEarly

@Suppress("unused")
val forceDefaultPlaybackSpeedPatch = patch(
    name = "Force default playback speed",
    description = "Forces a default playback speed (configurable) instead of resetting to 1.0x on each video.",
    default = false,
) {
    category("Interaction")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        PlaybackSpeedControllerFingerprint.method.returnEarly()
    }
}