package app.morphe.patches.youtube.ads

import app.morphe.patcher.patch.patch
import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.util.hideViewAfterConstruction

@Suppress("unused")
val blockAdOverlaysPatch = patch(
    name = "Block ad brand/CTA overlays",
    description = "Hides the advertiser brand interaction card (`BrandInteractionView`) and the ad info chip " +
        "(`InfoChip`) so they never cover the player.",
    default = true,
) {
    category("Ads")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        BrandInteractionCtorFingerprint.method.hideViewAfterConstruction()
        InfoChipCtorFingerprint.method.hideViewAfterConstruction()
    }
}

@Suppress("unused")
val blockAdTextOverlaysPatch = patch(
    name = "Block ad countdown/progress text overlays",
    description = "Hides the ad countdown timer (`AdCountdownTextView`) and the ad progress label " +
        "(`AdProgressTextView`).",
    default = true,
) {
    category("Ads")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        AdCountdownTextViewCtorFingerprint.method.hideViewAfterConstruction()
        AdProgressTextViewCtorFingerprint.method.hideViewAfterConstruction()
    }
}