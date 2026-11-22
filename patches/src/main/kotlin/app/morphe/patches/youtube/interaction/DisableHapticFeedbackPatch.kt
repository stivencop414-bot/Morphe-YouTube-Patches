package app.morphe.patches.youtube.interaction

import app.morphe.patcher.patch.patch
import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.patches.youtube.misc.HapticFeedbackPerformFingerprint
import app.morphe.util.returnEarly

@Suppress("unused")
val disableHapticFeedbackPatch = patch(
    name = "Disable haptic feedback",
    description = "Disables all haptic feedback (vibration) in the app.",
    default = false,
) {
    category("Interaction")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        HapticFeedbackPerformFingerprint.method.returnEarly()
    }
}