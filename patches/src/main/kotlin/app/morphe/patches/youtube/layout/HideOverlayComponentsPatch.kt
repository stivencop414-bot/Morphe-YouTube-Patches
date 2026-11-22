package app.morphe.patches.youtube.layout

import app.morphe.patcher.patch.patch
import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.util.hideViewAfterConstruction

@Suppress("unused")
val hideFullscreenEngagementPanelPatch = patch(
    name = "Hide fullscreen engagement panel",
    description = "Hides the engagement panel that slides up in fullscreen (related videos, comments).",
    default = false,
) {
    category("Layout")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        FullscreenEngagementPanelCtorFingerprint.method.hideViewAfterConstruction()
    }
}

@Suppress("unused")
val hidePlayerOverlayContainersPatch = patch(
    name = "Hide player overlay containers",
    description = "Hides the inline player overlay (`InlinePlayerOverlayLayout`) and the main player overlay " +
        "container (`YouTubePlayerOverlaysLayout`). Choosing this removes most on-video UI.",
    default = false,
) {
    category("Layout")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        InlinePlayerOverlayCtorFingerprint.method.hideViewAfterConstruction()
        YouTubePlayerOverlaysCtorFingerprint.method.hideViewAfterConstruction()
    }
}