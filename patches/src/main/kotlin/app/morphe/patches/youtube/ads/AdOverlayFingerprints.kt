package app.morphe.patches.youtube.ads

import app.morphe.patcher.Fingerprint
import com.android.tools.smali.dexlib2.AccessFlags

internal const val BRAND_INTERACTION_VIEW_DESCRIPTOR =
    "Lcom/google/android/libraries/youtube/ads/player/ui/BrandInteractionView;"
internal const val INFO_CHIP_DESCRIPTOR =
    "Lcom/google/android/libraries/youtube/ads/player/ui/InfoChip;"
internal const val AD_COUNTDOWN_TEXT_VIEW_DESCRIPTOR =
    "Lcom/google/android/libraries/youtube/ads/player/ui/AdCountdownTextView;"
internal const val AD_PROGRESS_TEXT_VIEW_DESCRIPTOR =
    "Lcom/google/android/libraries/youtube/ads/player/ui/AdProgressTextView;"

/**
 * `BrandInteractionView` is the advertiser brand card / CTA overlay.
 */
internal object BrandInteractionCtorFingerprint : Fingerprint(
    definingClass = BRAND_INTERACTION_VIEW_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)

/**
 * `InfoChip` is the small ad info chip on the player overlay.
 */
internal object InfoChipCtorFingerprint : Fingerprint(
    definingClass = INFO_CHIP_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)

/**
 * `AdCountdownTextView` renders the "skip in X" countdown text.
 */
internal object AdCountdownTextViewCtorFingerprint : Fingerprint(
    definingClass = AD_COUNTDOWN_TEXT_VIEW_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)

/**
 * `AdProgressTextView` renders the ad progress label.
 */
internal object AdProgressTextViewCtorFingerprint : Fingerprint(
    definingClass = AD_PROGRESS_TEXT_VIEW_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)