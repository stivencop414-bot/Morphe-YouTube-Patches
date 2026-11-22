package app.morphe.patches.youtube.misc

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.methodCall
import app.morphe.patcher.string
import com.android.tools.smali.dexlib2.AccessFlags

internal const val CONSENT_WEBVIEW_DESCRIPTOR = "Lvdq;"

/**
 * Consent WebView handler `Lvdq;->onUserConsent(Ljava/lang/String;)V`.
 *
 * Observed in 21.46.523: single method with `@JavascriptInterface` that forwards
 * consent string to `Lvdo;->a(Lvdn;)V`. The WebView is likely the OneTrust/GDPR
 * consent dialog. Disabling this prevents the consent prompt from completing,
 * effectively bypassing the delay.
 */
internal object ConsentWebViewFingerprint : Fingerprint(
    definingClass = CONSENT_WEBVIEW_DESCRIPTOR,
    name = "onUserConsent",
    accessFlags = listOf(AccessFlags.PUBLIC),
    returnType = "V",
    parameters = listOf("Ljava/lang/String;"),
)