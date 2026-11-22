package app.morphe.patches.youtube.player

import app.morphe.patcher.patch.patch
import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.util.hideViewAfterConstruction

@Suppress("unused")
val disableQuickSeekBloomPatch = patch(
    name = "Disable quick-seek tap bloom animation",
    description = "Hides the animated ripple (`TapBloomView`, `CircularClipTapBloomView`) shown while scrubbing.",
    default = false,
) {
    category("Player")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        TapBloomViewCtorFingerprint.method.hideViewAfterConstruction()
        CircularClipTapBloomCtorFingerprint.method.hideViewAfterConstruction()
    }
}

@Suppress("unused")
val disableHeatMarkerPatch = patch(
    name = "Disable video heatmap/chapter markers",
    description = "Hides the chapter/heatmap markers overlay (`HeatMarkerView`) on the seek bar.",
    default = false,
) {
    category("Player")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        HeatMarkerViewCtorFingerprint.method.hideViewAfterConstruction()
    }
}

@Suppress("unused")
val disableSubtitlesPatch = patch(
    name = "Disable subtitle rendering",
    description = "Hides both YouTube's native `SubtitleWindowView` and the ExoPlayer/Media3 `SubtitleView`.",
    default = false,
) {
    category("Player")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        SubtitleWindowViewCtorFingerprint.method.hideViewAfterConstruction()
        SubtitleViewCtorFingerprint.method.hideViewAfterConstruction()
    }
}

@Suppress("unused")
val disablePlayerSupplementalUiPatch = patch(
    name = "Hide supplemental player surfaces",
    description = "Hides the core `PlayerView` container and the VR welcome screen. Intended only for testing: " +
        "hiding `PlayerView` removes video rendering entirely.",
    default = false,
) {
    category("Player")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        PlayerViewCtorFingerprint.method.hideViewAfterConstruction()
    }
}