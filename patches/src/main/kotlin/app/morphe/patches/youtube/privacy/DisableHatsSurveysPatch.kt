package app.morphe.patches.youtube.privacy

import app.morphe.patcher.patch.patch
import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.util.hideViewAfterConstruction

@Suppress("unused")
val disableHatsSurveysPatch = patch(
    name = "Disable HATS surveys",
    description = "Hides Google HATS (Human Ads Testing System) survey prompts (`HatsSurvey`, `HatsContainer`, " +
        "`HatsHorizontalSurvey`).",
    default = true,
) {
    category("Privacy")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        HatsSurveyCtorFingerprint.method.hideViewAfterConstruction()
        HatsContainerCtorFingerprint.method.hideViewAfterConstruction()
        HatsHorizontalSurveyCtorFingerprint.method.hideViewAfterConstruction()
    }
}