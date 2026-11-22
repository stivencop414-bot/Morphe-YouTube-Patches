package app.morphe.patches.youtube.misc

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.methodCall
import com.android.tools.smali.dexlib2.AccessFlags

internal const val INNERTUBE_ENDPOINT_ENUM_DESCRIPTOR = "Laleq;"

/**
 * `Laleq` enum - defines Innertube API endpoints.
 *
 * Observed values in 21.46.523:
 * - PRODUCTION (0): https://youtubei.googleapis.com
 * - AUTOPUSH (1): https://green-youtubei.sandbox.googleapis.com
 * - STAGING (2): https://release-youtubei.sandbox.googleapis.com
 * - TEST (3): https://test-youtubei.sandbox.googleapis.com
 * - uYTFE (4): https://uytfe.sandbox.google.com
 * - PPG (5): http://127.0.0.1:8787 (localhost dev)
 *
 * Each value has fields: h:I (index), i:Landroid/net/Uri, j:Ljava/lang/String (hostname)
 */
internal object InnertubeEndpointEnumFingerprint : Fingerprint(
    definingClass = INNERTUBE_ENDPOINT_ENUM_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL, AccessFlags.STATIC, AccessFlags.ENUM),
    filters = listOf(
        app.morphe.patcher.string("http://127.0.0.1:8787"),
    ),
)

/**
 * Documented for reference: endpoint selection logic in `Ljdw` uses `Laleq;->values()`
 * and matches by the `h:I` index field. To use a custom endpoint:
 *
 * 1. Set the `aleq` enum to PPG (index 5) which points to localhost
 * 2. Run a local proxy on http://127.0.0.1:8787 that forwards to your custom backend
 * 3. Or use ADB to modify the endpoint at runtime:
 *    `adb shell setprop debug.youtube.innertube_endpoint "https://your-proxy.example.com/youtubei/v1"`
 *
 * A full Morphe extension would be needed for a proper Settings UI integration.
 */