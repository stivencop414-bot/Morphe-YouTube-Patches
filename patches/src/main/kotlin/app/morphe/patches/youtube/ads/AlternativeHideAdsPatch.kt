package app.morphe.patches.youtube.ads

import app.morphe.patcher.patch.patch
import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.util.hideViewAfterConstruction

@Suppress("unused")
val alternativeHideAdsPatch = patch(
    name = "Alternative Hide Ads (clean-room)",
    description = "Hides the five ad player UI components (`AdHighlightLayout`, `SkipAdButton`, `AdCountdownView`, " +
        "`AdProgressView`, `AdDisclosureBannerView`) after construction so they never become visible.",
    default = true,
) {
    category("Ads")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        AdHighlightCtorFingerprint.method.hideViewAfterConstruction()
        SkipAdButtonCtorFingerprint.method.hideViewAfterConstruction()
        AdCountdownCtorFingerprint.method.hideViewAfterConstruction()
        AdProgressCtorFingerprint.method.hideViewAfterConstruction()
        AdDisclosureCtorFingerprint.method.hideViewAfterConstruction()
    }
}