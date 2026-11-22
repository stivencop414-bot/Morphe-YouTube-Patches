package app.morphe.patches.youtube.misc

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.methodCall
import app.morphe.patcher.string
import com.android.tools.smali.dexlib2.AccessFlags

internal const val SHORTS_CONTROLLER_DESCRIPTOR = "Lcom/google/android/apps/youtube/app/shorts/ShortsController;"

/**
 * Shorts controller - allows hiding Shorts shelf/entry points.
 */
internal object ShortsControllerFingerprint : Fingerprint(
    definingClass = SHORTS_CONTROLLER_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "V",
    filters = listOf(
        methodCall(definingClass = "Landroid/view/View;", name = "setVisibility"),
        string("shorts"),
    ),
)