package app.morphe.patches.youtube.ads

import app.morphe.patcher.patch.patch
import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.util.hideViewAfterConstruction
import app.morphe.util.returnEarly

@Suppress("unused")
val blockAdWebViewsPatch = patch(
    name = "Block ad WebViews",
    description = "Hides `AdsWebView` and `PlayableAdWebView` after construction so display and playable ad " +
        "pages never become visible, and stops `AdsWebView` from attaching to a window.",
    default = true,
) {
    category("Ads")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        AdsWebViewContextCtorFingerprint.method.hideViewAfterConstruction()
        AdsWebViewAttributeSetCtorFingerprint.method.hideViewAfterConstruction()
        AdsWebViewAttachFingerprint.method.returnEarly()
        PlayableAdWebViewCtorFingerprint.method.hideViewAfterConstruction()
    }
}