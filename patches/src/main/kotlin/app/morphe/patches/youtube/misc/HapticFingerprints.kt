package app.morphe.patches.youtube.misc

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.methodCall
import com.android.tools.smali.dexlib2.AccessFlags

internal const val HAPTIC_FEEDBACK_DESCRIPTOR = "Landroid/view/HapticFeedbackConstants;"

/**
 * Haptic feedback caller - matched by the vibrator service call pattern.
 * Used to disable haptic feedback globally.
 */
internal object HapticFeedbackPerformFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "Z",
    parameters = listOf("I"),
    filters = listOf(
        methodCall(definingClass = "Landroid/os/Vibrator;", name = "vibrate"),
    ),
)