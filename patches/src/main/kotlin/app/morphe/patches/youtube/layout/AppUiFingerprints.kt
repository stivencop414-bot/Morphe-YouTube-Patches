package app.morphe.patches.youtube.layout

import app.morphe.patcher.Fingerprint
import com.android.tools.smali.dexlib2.AccessFlags

internal const val CLING_TUTORIAL_VIEW_DESCRIPTOR =
    "Lcom/google/android/apps/youtube/app/common/tutorial/ClingTutorialView;"
internal const val CLING_VIEW_DESCRIPTOR =
    "Lcom/google/android/apps/youtube/app/common/tutorial/ClingView;"
internal const val YOUTUBE_SCRUBBED_PREVIEW_DESCRIPTOR =
    "Lcom/google/android/apps/youtube/app/player/overlay/storyboard/YoutubeScrubbedPreviewView;"
internal const val REEL_SCRUBBED_PREVIEW_DESCRIPTOR =
    "Lcom/google/android/apps/youtube/app/extensions/reel/watch/player/ReelScrubbedPreviewView;"
internal const val OFFLINE_ARROW_VIEW_DESCRIPTOR =
    "Lcom/google/android/apps/youtube/app/offline/ui/OfflineArrowView;"

/**
 * `ClingTutorialView` shows first-run "cling" tutorial popups with a dismissable
 * coach mark.
 */
internal object ClingTutorialViewCtorFingerprint : Fingerprint(
    definingClass = CLING_TUTORIAL_VIEW_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;", "Landroid/util/AttributeSet;"),
)

/**
 * `ClingView` is the coach-mark bubble used by the tutorials above.
 */
internal object ClingViewCtorFingerprint : Fingerprint(
    definingClass = CLING_VIEW_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;", "Landroid/util/AttributeSet;"),
)

/**
 * `YoutubeScrubbedPreviewView` renders the storyboard thumbnail preview while
 * scrubbing the main player seek bar.
 */
internal object YoutubeScrubbedPreviewCtorFingerprint : Fingerprint(
    definingClass = YOUTUBE_SCRUBBED_PREVIEW_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)

/**
 * `ReelScrubbedPreviewView` is the Shorts scrub preview.
 */
internal object ReelScrubbedPreviewCtorFingerprint : Fingerprint(
    definingClass = REEL_SCRUBBED_PREVIEW_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)

/**
 * `OfflineArrowView` is the download arrow indicator on thumbnails.
 */
internal object OfflineArrowViewCtorFingerprint : Fingerprint(
    definingClass = OFFLINE_ARROW_VIEW_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)