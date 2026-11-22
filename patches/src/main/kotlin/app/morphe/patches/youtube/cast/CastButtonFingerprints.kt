package app.morphe.patches.youtube.cast

import app.morphe.patcher.Fingerprint
import com.android.tools.smali.dexlib2.AccessFlags

internal const val MDX_MEDIA_ROUTE_BUTTON_DESCRIPTOR =
    "Lcom/google/android/libraries/youtube/mdx/mediaroute/MdxMediaRouteButton;"
internal const val MDX_ENTRY_POINT_BUTTON_DESCRIPTOR =
    "Lcom/google/android/libraries/youtube/mdx/mediaroute/entrypoint/MdxEntryPointButton;"

/**
 * `MdxMediaRouteButton` is the Cast device picker button in the action bar/toolbar.
 */
internal object MdxMediaRouteButtonCtorFingerprint : Fingerprint(
    definingClass = MDX_MEDIA_ROUTE_BUTTON_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)

/**
 * `MdxEntryPointButton` is the Cast entry point in the player overlay.
 */
internal object MdxEntryPointButtonCtorFingerprint : Fingerprint(
    definingClass = MDX_ENTRY_POINT_BUTTON_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)