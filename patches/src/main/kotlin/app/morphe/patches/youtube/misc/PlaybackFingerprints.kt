package app.morphe.patches.youtube.misc

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.methodCall
import app.morphe.patcher.string
import com.android.tools.smali.dexlib2.AccessFlags

internal const val PLAYBACK_SPEED_DESCRIPTOR = "Lcom/google/android/libraries/youtube/player/features/speed/PlaybackSpeedController;"

/**
 * Playback speed controller - allows setting default playback speed.
 */
internal object PlaybackSpeedControllerFingerprint : Fingerprint(
    definingClass = PLAYBACK_SPEED_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "V",
    filters = listOf(
        methodCall(definingClass = "Landroid/content/SharedPreferences;", name = "getFloat"),
        string("playback_speed"),
    ),
)