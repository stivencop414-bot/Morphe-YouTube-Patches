package app.morphe.patches.youtube.ads

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.methodCall
import com.android.tools.smali.dexlib2.AccessFlags

internal const val ADS_WEBVIEW_DESCRIPTOR = "Lcom/google/android/libraries/youtube/ads/ui/webview/AdsWebView;"
internal const val PLAYABLE_AD_WEBVIEW_DESCRIPTOR = "Lcom/google/android/libraries/youtube/ads/ui/webview/PlayableAdWebView;"
internal const val ADS_INLINE_WEBSITE_DESCRIPTOR = "Lcom/google/android/libraries/youtube/ads/ui/webview/AdsInlineWebsite;"

/**
 * `AdsWebView` constructors.
 *
 * Observed in 21.46.523: five constructors (`Context`, `Context+AttributeSet`,
 * +`I`, +`II`, +`IZ`). This is the WebView host for display and playable ads.
 * Returning early prevents the WebView from being created.
 */
internal object AdsWebViewContextCtorFingerprint : Fingerprint(
    definingClass = ADS_WEBVIEW_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)

internal object AdsWebViewAttributeSetCtorFingerprint : Fingerprint(
    definingClass = ADS_WEBVIEW_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;", "Landroid/util/AttributeSet;"),
)

/**
 * `AdsWebView.onAttachedToWindow()`.
 *
 * Observed: calls `WebView.onAttachedToWindow`, then resolves its parent view and
 * forwards into the ad rendering path.
 */
internal object AdsWebViewAttachFingerprint : Fingerprint(
    definingClass = ADS_WEBVIEW_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PROTECTED, AccessFlags.FINAL),
    returnType = "V",
    parameters = emptyList(),
    filters = listOf(
        methodCall(definingClass = "Landroid/webkit/WebView;", name = "onAttachedToWindow"),
    ),
)

/**
 * `PlayableAdWebView` (interactive ad container).
 */
internal object PlayableAdWebViewCtorFingerprint : Fingerprint(
    definingClass = PLAYABLE_AD_WEBVIEW_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;", "Landroid/util/AttributeSet;"),
)