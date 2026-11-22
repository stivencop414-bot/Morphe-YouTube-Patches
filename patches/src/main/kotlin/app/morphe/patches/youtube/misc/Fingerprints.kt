package app.morphe.patches.youtube.misc

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.methodCall
import app.morphe.patcher.string
import com.android.tools.smali.dexlib2.AccessFlags

internal const val FIREBASE_ANALYTICS_DESCRIPTOR = "Lcom/google/firebase/analytics/FirebaseAnalytics;"
internal const val FIREBASE_HELPER_DESCRIPTOR = "Lito;"
internal const val FIREBASE_TOGGLE_DESCRIPTOR = "Laldk;"
internal const val NEW_VERSION_ACTIVITY_DESCRIPTOR =
    "Lcom/google/android/apps/youtube/app/application/upgrade/NewVersionAvailableActivity;"
internal const val SURFACE_VIEW_SECURE_DESCRIPTOR = "Laqbl;"

/**
 * `Laldk;->a()V` is the analytics collection toggler.
 *
 * Observed in 21.46.523: reads an `Integer` feature flag, compares it against
 * `0xf73140`, then calls `FirebaseAnalytics.a(Z)` with `1` or `0`. The class also
 * declares a `(Landroid/content/Context;Laldm;Lbwqg;Lbwqg;)V` constructor and
 * the `b:Z` / `c:Z` state fields.
 */
internal object FirebaseCollectionToggleFingerprint : Fingerprint(
    definingClass = FIREBASE_TOGGLE_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "V",
    parameters = emptyList(),
    filters = listOf(
        methodCall(definingClass = "Ljava/lang/Integer;", name = "intValue"),
        methodCall(definingClass = FIREBASE_ANALYTICS_DESCRIPTOR, name = "a"),
    ),
)

/**
 * `NewVersionAvailableActivity.onCreate(Bundle)`.
 *
 * Observed: `invoke-super ... Lizm;->onCreate`, `Lrd;->setContentView(I)`, then
 * `Intent.getParcelableExtra("forward_intent")` and
 * `Intent.getBooleanExtra("show_force_upgrade", false)`.
 */
internal object NewVersionActivityOnCreateFingerprint : Fingerprint(
    definingClass = NEW_VERSION_ACTIVITY_DESCRIPTOR,
    name = "onCreate",
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "V",
    parameters = listOf("Landroid/os/Bundle;"),
    filters = listOf(
        methodCall(definingClass = "Landroid/content/Intent;", name = "getParcelableExtra"),
        methodCall(definingClass = "Landroid/content/Intent;", name = "getBooleanExtra"),
        string("show_force_upgrade"),
    ),
)

/**
 * Secure surface wrapper `Laqbl;-><init>(Landroid/content/Context;Laqan;)`.
 *
 * Observed: the only `SurfaceView.setSecure` call site in the APK. It reads the
 * inherited `e:Landroid/view/SurfaceView;` field and calls `setSecure(true)`.
 */
internal object SecureSurfaceViewFingerprint : Fingerprint(
    definingClass = SURFACE_VIEW_SECURE_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    filters = listOf(
        methodCall(definingClass = "Landroid/view/SurfaceView;", name = "setSecure"),
    ),
)