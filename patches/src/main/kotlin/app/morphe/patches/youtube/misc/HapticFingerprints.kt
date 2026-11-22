package app.morphe.patches.youtube.misc

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.methodCall
import com.android.tools.smali.dexlib2.AccessFlags

internal const val HAPTIC_HELPER_DESCRIPTOR = "Lkae;"

/**
 * Central haptic helper `Lkae;->a()V`.
 *
 * Observed in 21.46.523: this class holds `a:Landroid/os/Vibrator` and
 * `b:Landroid/os/VibrationEffect` fields, and its only method `a()` calls
 * `Vibrator.vibrate(J)`. Returning early from it disables the shared haptic
 * path instead of patching every call site.
 */
internal object HapticFeedbackPerformFingerprint : Fingerprint(
    definingClass = HAPTIC_HELPER_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "V",
    parameters = emptyList(),
    filters = listOf(
        methodCall(definingClass = "Landroid/os/Vibrator;", name = "vibrate"),
    ),
)