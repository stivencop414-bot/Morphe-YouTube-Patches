package app.morphe.patches.youtube.layout

import app.morphe.patcher.Fingerprint
import com.android.tools.smali.dexlib2.AccessFlags

internal const val FULLSCREEN_ENGAGEMENT_PANEL_DESCRIPTOR =
    "Lcom/google/android/apps/youtube/app/player/overlay/FullscreenEngagementPanelCoordinatorLayout;"
internal const val INLINE_PLAYER_OVERLAY_DESCRIPTOR =
    "Lcom/google/android/apps/youtube/app/common/ui/inline/InlinePlayerOverlayLayout;"
internal const val YOUTUBE_PLAYER_OVERLAYS_DESCRIPTOR =
    "Lcom/google/android/apps/youtube/app/common/player/overlay/YouTubePlayerOverlaysLayout;"

/**
 * `FullscreenEngagementPanelCoordinatorLayout` hosts the fullscreen engagement panel
 * (related videos, comments) that slides up over the player.
 */
internal object FullscreenEngagementPanelCtorFingerprint : Fingerprint(
    definingClass = FULLSCREEN_ENGAGEMENT_PANEL_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)

/**
 * `InlinePlayerOverlayLayout` is the overlay drawn above inline/feed playback.
 */
internal object InlinePlayerOverlayCtorFingerprint : Fingerprint(
    definingClass = INLINE_PLAYER_OVERLAY_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)

/**
 * `YouTubePlayerOverlaysLayout` is the main player overlay container.
 */
internal object YouTubePlayerOverlaysCtorFingerprint : Fingerprint(
    definingClass = YOUTUBE_PLAYER_OVERLAYS_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)