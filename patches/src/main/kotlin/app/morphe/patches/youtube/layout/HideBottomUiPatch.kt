package app.morphe.patches.youtube.layout

import app.morphe.patcher.patch.patch
import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.util.hideViewAfterConstruction

@Suppress("unused")
val hidePromoSnackbarsPatch = patch(
    name = "Hide promo/announcement snackbars",
    description = "Hides YouTube's bottom promo bar (`Mealbar`) used for feature announcements and offers.",
    default = true,
) {
    category("Layout")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        MealbarCtorFingerprint.method.hideViewAfterConstruction()
    }
}

@Suppress("unused")
val hideCinematicBackdropPatch = patch(
    name = "Hide cinematic ambient backdrop",
    description = "Hides the blurred `CinematicImageView` backdrop behind watch pages (saves battery/rendering).",
    default = false,
) {
    category("Layout")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        CinematicImageViewCtorFingerprint.method.hideViewAfterConstruction()
    }
}

@Suppress("unused")
val hideChipCloudPatch = patch(
    name = "Hide filter chip cloud",
    description = "Hides the horizontal filter chips (`ChipCloudView`) above feeds and results.",
    default = false,
) {
    category("Layout")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        ChipCloudViewCtorFingerprint.method.hideViewAfterConstruction()
    }
}

@Suppress("unused")
val hideNavBarDividerPatch = patch(
    name = "Hide navigation bar divider",
    description = "Hides the divider line above the bottom navigation bar (`NavigationBarDividerLayout`).",
    default = false,
) {
    category("Layout")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        NavigationBarDividerCtorFingerprint.method.hideViewAfterConstruction()
    }
}