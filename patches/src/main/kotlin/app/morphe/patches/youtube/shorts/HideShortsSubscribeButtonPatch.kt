package app.morphe.patches.youtube.shorts

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.patch.patch
import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.util.hideViewAfterConstruction
import com.android.tools.smali.dexlib2.AccessFlags

internal const val SUBSCRIBE_BUTTON_SHORTS_DESCRIPTOR =
    "Lcom/google/android/apps/youtube/app/extensions/reel/watch/overlay/SubscribeButton;"

internal object SubscribeButtonShortsCtorFingerprint : Fingerprint(
    definingClass = SUBSCRIBE_BUTTON_SHORTS_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)

@Suppress("unused")
val hideShortsSubscribeButtonPatch = patch(
    name = "Hide Subscribe button on Shorts",
    description = "Hides the Subscribe button overlay on Shorts.",
    default = false,
) {
    category("Shorts")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        SubscribeButtonShortsCtorFingerprint.method.hideViewAfterConstruction()
    }
}