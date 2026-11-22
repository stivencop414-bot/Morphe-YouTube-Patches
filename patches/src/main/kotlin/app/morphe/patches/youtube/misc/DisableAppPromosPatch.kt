package app.morphe.patches.youtube.misc

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.string
import app.morphe.patcher.patch.patch
import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.util.returnEarly
import com.android.tools.smali.dexlib2.AccessFlags

internal const val APP_PROMO_CONTROLLER_DESCRIPTOR = "Laebr;"

/**
 * `Laebr` appears to control app promo feature flags.
 *
 * Observed in 21.46.523: single field `a:Z` and string "appPromoEnabled".
 * The methods `b(I)`, `c(Z)`, `d(Z)`, `e(Z)`, `f(Z)`, `g(Z)` likely manipulate
 * promo visibility. Returning early from `b(I)` (which takes an int, likely
 * a promo type) prevents promo display.
 */
internal object AppPromoControllerFingerprint : Fingerprint(
    definingClass = APP_PROMO_CONTROLLER_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "V",
    parameters = listOf("I"),
)

@Suppress("unused")
val disableAppPromosPatch = patch(
    name = "Disable app promotional popups",
    description = "Disables in-app promotional banners and upgrade prompts controlled by the `Laebr` feature flag system.",
    default = false,
) {
    category("Misc")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        AppPromoControllerFingerprint.method.returnEarly()
    }
}