package app.morphe.patches.youtube.livechat

import app.morphe.patcher.patch.patch
import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.util.hideViewAfterConstruction

@Suppress("unused")
val hideLiveChatComponentsPatch = patch(
    name = "Hide live chat components",
    description = "Hides the live chat message list (`LiveChatRecyclerView`), banners, and swipeable container. " +
        "Choosing this removes the in-chat UI for live streams.",
    default = false,
) {
    category("Live")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        LiveChatRecyclerViewCtorFingerprint.method.hideViewAfterConstruction()
        LiveChatBannerCtorFingerprint.method.hideViewAfterConstruction()
        LiveChatSwipeableCtorFingerprint.method.hideViewAfterConstruction()
    }
}