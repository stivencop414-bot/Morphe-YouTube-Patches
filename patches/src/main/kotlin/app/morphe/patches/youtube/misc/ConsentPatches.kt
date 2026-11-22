package app.morphe.patches.youtube.misc

import app.morphe.patcher.patch.patch
import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.patches.youtube.misc.ConsentWebViewFingerprint
import app.morphe.util.returnEarly

@Suppress("unused")
val bypassConsentDialogPatch = patch(
    name = "Bypass consent dialog delay",
    description = "Skips the OneTrust/GDPR consent WebView initialization at startup. The consent " +
        "prompt is dismissed immediately, avoiding the blocking delay on launch.",
    default = false,
) {
    category("Privacy")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        ConsentWebViewFingerprint.method.returnEarly()
    }
}