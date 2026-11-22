package app.morphe.patches.youtube.ads

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.methodCall
import app.morphe.patcher.patch.patch
import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.util.returnEarly
import com.android.tools.smali.dexlib2.AccessFlags

internal const val AD_HIGHLIGHT_LAYOUT = "Lcom/google/android/apps/youtube/app/player/overlay/ads/AdHighlightLayout;"
internal const val SKIP_AD_BUTTON = "Lcom/google/android/libraries/youtube/ads/player/ui/SkipAdButton;"
internal const val AD_COUNTDOWN = "Lcom/google/android/libraries/youtube/ads/player/ui/AdCountdownView;"
internal const val AD_PROGRESS = "Lcom/google/android/libraries/youtube/ads/player/ui/AdProgressView;"
internal const val AD_DISCLOSURE = "Lcom/google/android/libraries/youtube/ads/player/ui/AdDisclosureBannerView;"

internal object AdHighlightCtorFingerprint : Fingerprint(
    definingClass = AD_HIGHLIGHT_LAYOUT,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;", "Landroid/util/AttributeSet;"),
    filters = listOf(
        methodCall(definingClass = "Lcom/google/android/apps/youtube/app/player/overlay/fullscreenengagement/layout/MetadataHighlightsColumnLinearLayout;", name = "<init>"),
    ),
)

internal object SkipAdButtonCtorFingerprint : Fingerprint(
    definingClass = SKIP_AD_BUTTON,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;", "Landroid/util/AttributeSet;"),
)

internal object AdCountdownCtorFingerprint : Fingerprint(
    definingClass = AD_COUNTDOWN,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;", "Landroid/util/AttributeSet;"),
)

internal object AdProgressCtorFingerprint : Fingerprint(
    definingClass = AD_PROGRESS,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;", "Landroid/util/AttributeSet;"),
)

internal object AdDisclosureCtorFingerprint : Fingerprint(
    definingClass = AD_DISCLOSURE,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;", "Landroid/util/AttributeSet;"),
)

@Suppress("UNUSED_PARAMETER")
val alternativeHideAdsPatch = patch(
    name = "Alternative Hide Ads (clean-room)",
    description = "Blocks ad UI components by preventing their construction. " +
        "Targets: AdHighlightLayout, SkipAdButton, AdCountdownView, AdProgressView, AdDisclosureBannerView. " +
        "Clean-room reimplementation - different injection points than official bundle.",
    default = true,
) {
    category("Ads")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        AdHighlightCtorFingerprint.method.returnEarly()
        SkipAdButtonCtorFingerprint.method.returnEarly()
        AdCountdownCtorFingerprint.method.returnEarly()
        AdProgressCtorFingerprint.method.returnEarly()
        AdDisclosureCtorFingerprint.method.returnEarly()
    }
}