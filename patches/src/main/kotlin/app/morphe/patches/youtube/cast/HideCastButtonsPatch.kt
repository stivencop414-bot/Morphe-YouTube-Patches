package app.morphe.patches.youtube.cast

import app.morphe.patcher.patch.patch
import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.util.hideViewAfterConstruction

@Suppress("unused")
val hideCastButtonsPatch = patch(
    name = "Hide Cast buttons",
    description = "Hides the Cast device picker button (`MdxMediaRouteButton`) and the player overlay Cast " +
        "entry point (`MdxEntryPointButton`).",
    default = false,
) {
    category("Cast")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        MdxMediaRouteButtonCtorFingerprint.method.hideViewAfterConstruction()
        MdxEntryPointButtonCtorFingerprint.method.hideViewAfterConstruction()
    }
}