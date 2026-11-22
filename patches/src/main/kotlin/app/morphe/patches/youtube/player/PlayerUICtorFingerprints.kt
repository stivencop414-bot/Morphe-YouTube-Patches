package app.morphe.patches.youtube.player

import app.morphe.patcher.Fingerprint
import com.android.tools.smali.dexlib2.AccessFlags

internal const val PLAYER_VIEW_DESCRIPTOR =
    "Lcom/google/android/libraries/youtube/player/ui/PlayerView;"
internal const val BACKGROUND_PLAYER_SERVICE_DESCRIPTOR =
    "Lcom/google/android/libraries/youtube/player/background/service/BackgroundPlayerService;"
internal const val VR_WELCOME_ACTIVITY_DESCRIPTOR =
    "Lcom/google/android/libraries/youtube/player/features/gl/vr/VrWelcomeActivity;"
internal const val TAP_BLOOM_VIEW_DESCRIPTOR =
    "Lcom/google/android/libraries/youtube/player/features/quickseek/overlay/TapBloomView;"
internal const val CIRCULAR_CLIP_TAP_BLOOM_DESCRIPTOR =
    "Lcom/google/android/libraries/youtube/player/features/quickseek/overlay/CircularClipTapBloomView;"
internal const val HEAT_MARKER_VIEW_DESCRIPTOR =
    "Lcom/google/android/libraries/youtube/player/features/markers/HeatMarkerView;"
internal const val SUBTITLE_WINDOW_VIEW_DESCRIPTOR =
    "Lcom/google/android/libraries/youtube/player/subtitles/ui/SubtitleWindowView;"
internal const val SUBTITLE_VIEW_DESCRIPTOR =
    "Landroidx/media3/ui/SubtitleView;"

/**
 * `PlayerView` is the core player view container.
 */
internal object PlayerViewCtorFingerprint : Fingerprint(
    definingClass = PLAYER_VIEW_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)

/**
 * `BackgroundPlayerService` handles background playback.
 */
internal object BackgroundPlayerServiceCtorFingerprint : Fingerprint(
    definingClass = BACKGROUND_PLAYER_SERVICE_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = emptyList(),
)

/**
 * `VrWelcomeActivity` is shown for VR mode introduction.
 */
internal object VrWelcomeActivityCtorFingerprint : Fingerprint(
    definingClass = VR_WELCOME_ACTIVITY_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = emptyList(),
)

/**
 * `TapBloomView` is the animated ripple shown on quick-seek tap.
 */
internal object TapBloomViewCtorFingerprint : Fingerprint(
    definingClass = TAP_BLOOM_VIEW_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)

/**
 * `CircularClipTapBloomView` is the circular bloom for quick-seek.
 */
internal object CircularClipTapBloomCtorFingerprint : Fingerprint(
    definingClass = CIRCULAR_CLIP_TAP_BLOOM_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)

/**
 * `HeatMarkerView` renders the heatmap/chapter markers on the seek bar.
 */
internal object HeatMarkerViewCtorFingerprint : Fingerprint(
    definingClass = HEAT_MARKER_VIEW_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)

/**
 * `SubtitleWindowView` is YouTube's native subtitle rendering view.
 */
internal object SubtitleWindowViewCtorFingerprint : Fingerprint(
    definingClass = SUBTITLE_WINDOW_VIEW_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)

/**
 * `SubtitleView` from ExoPlayer/Media3 library.
 */
internal object SubtitleViewCtorFingerprint : Fingerprint(
    definingClass = SUBTITLE_VIEW_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)