package app.morphe.patches.youtube.layout

import app.morphe.patcher.Fingerprint
import com.android.tools.smali.dexlib2.AccessFlags

internal const val YOUTUBE_SNACKBAR_DESCRIPTOR =
    "Lcom/google/android/apps/youtube/app/common/ui/bottomui/YouTubeSnackbar;"
internal const val MEALBAR_DESCRIPTOR =
    "Lcom/google/android/apps/youtube/app/common/ui/bottomui/Mealbar;"
internal const val CHIP_CLOUD_VIEW_DESCRIPTOR =
    "Lcom/google/android/apps/youtube/app/common/ui/chipcloud/ChipCloudView;"
internal const val CINEMATIC_IMAGE_VIEW_DESCRIPTOR =
    "Lcom/google/android/apps/youtube/app/common/ui/cinematics/CinematicImageView;"
internal const val NAV_BAR_DIVIDER_DESCRIPTOR =
    "Lcom/google/android/apps/youtube/app/common/ui/navigationbar/NavigationBarDividerLayout;"

/**
 * `YouTubeSnackbar` shows transient in-app snackbars ("Added to playlist", etc.).
 */
internal object YouTubeSnackbarCtorFingerprint : Fingerprint(
    definingClass = YOUTUBE_SNACKBAR_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)

/**
 * `Mealbar` is YouTube's bottom snack/promo bar (feature announcements, offers).
 */
internal object MealbarCtorFingerprint : Fingerprint(
    definingClass = MEALBAR_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)

/**
 * `ChipCloudView` is the horizontal filter-chip cloud above feeds/results.
 */
internal object ChipCloudViewCtorFingerprint : Fingerprint(
    definingClass = CHIP_CLOUD_VIEW_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)

/**
 * `CinematicImageView` is the blurred ambient backdrop behind watch pages.
 */
internal object CinematicImageViewCtorFingerprint : Fingerprint(
    definingClass = CINEMATIC_IMAGE_VIEW_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)

/**
 * `NavigationBarDividerLayout` draws the divider above the bottom navigation bar.
 */
internal object NavigationBarDividerCtorFingerprint : Fingerprint(
    definingClass = NAV_BAR_DIVIDER_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)