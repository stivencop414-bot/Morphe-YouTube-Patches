package app.morphe.patches.youtube.layout

import app.morphe.patcher.patch.patch
import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.util.hideViewAfterConstruction

@Suppress("unused")
val hideTutorialClingsPatch = patch(
    name = "Hide tutorial clings",
    description = "Hides first-run coach marks and tutorial popups (`ClingTutorialView`, `ClingView`).",
    default = true,
) {
    category("Layout")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        ClingTutorialViewCtorFingerprint.method.hideViewAfterConstruction()
        ClingViewCtorFingerprint.method.hideViewAfterConstruction()
    }
}

@Suppress("unused")
val hideScrubPreviewPatch = patch(
    name = "Hide scrub preview thumbnails",
    description = "Hides the storyboard preview shown while dragging the seek bar (main player and Shorts).",
    default = false,
) {
    category("Player")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        YoutubeScrubbedPreviewCtorFingerprint.method.hideViewAfterConstruction()
        ReelScrubbedPreviewCtorFingerprint.method.hideViewAfterConstruction()
    }
}

@Suppress("unused")
val hideOfflineArrowPatch = patch(
    name = "Hide offline download arrows",
    description = "Hides the download availability arrow (`OfflineArrowView`) on video thumbnails.",
    default = false,
) {
    category("Layout")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        OfflineArrowViewCtorFingerprint.method.hideViewAfterConstruction()
    }
}