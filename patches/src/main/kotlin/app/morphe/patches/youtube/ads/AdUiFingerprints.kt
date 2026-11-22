package app.morphe.patches.youtube.ads

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.methodCall
import com.android.tools.smali.dexlib2.AccessFlags

internal const val AD_HIGHLIGHT_LAYOUT_DESCRIPTOR =
    "Lcom/google/android/apps/youtube/app/player/overlay/ads/AdHighlightLayout;"
internal const val SKIP_AD_BUTTON_DESCRIPTOR =
    "Lcom/google/android/libraries/youtube/ads/player/ui/SkipAdButton;"
internal const val AD_COUNTDOWN_VIEW_DESCRIPTOR =
    "Lcom/google/android/libraries/youtube/ads/player/ui/AdCountdownView;"
internal const val AD_PROGRESS_VIEW_DESCRIPTOR =
    "Lcom/google/android/libraries/youtube/ads/player/ui/AdProgressView;"
internal const val AD_DISCLOSURE_BANNER_DESCRIPTOR =
    "Lcom/google/android/libraries/youtube/ads/player/ui/AdDisclosureBannerView;"

/**
 * `AdHighlightLayout` wraps the yellow ad highlight drawn behind player metadata.
 */
internal object AdHighlightCtorFingerprint : Fingerprint(
    definingClass = AD_HIGHLIGHT_LAYOUT_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;", "Landroid/util/AttributeSet;"),
    filters = listOf(
        methodCall(
            definingClass = "Lcom/google/android/apps/youtube/app/player/overlay/" +
                "fullscreenengagement/layout/MetadataHighlightsColumnLinearLayout;",
            name = "<init>",
        ),
    ),
)

/**
 * `SkipAdButton` is the in-player "Skip Ad" control.
 */
internal object SkipAdButtonCtorFingerprint : Fingerprint(
    definingClass = SKIP_AD_BUTTON_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;", "Landroid/util/AttributeSet;"),
)

/**
 * `AdCountdownView` shows the remaining ad time.
 */
internal object AdCountdownCtorFingerprint : Fingerprint(
    definingClass = AD_COUNTDOWN_VIEW_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;", "Landroid/util/AttributeSet;"),
)

/**
 * `AdProgressView` shows the ad progress bar and label.
 */
internal object AdProgressCtorFingerprint : Fingerprint(
    definingClass = AD_PROGRESS_VIEW_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;", "Landroid/util/AttributeSet;"),
)

/**
 * `AdDisclosureBannerView` renders the "Sponsored" disclosure banner.
 */
internal object AdDisclosureCtorFingerprint : Fingerprint(
    definingClass = AD_DISCLOSURE_BANNER_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;", "Landroid/util/AttributeSet;"),
)