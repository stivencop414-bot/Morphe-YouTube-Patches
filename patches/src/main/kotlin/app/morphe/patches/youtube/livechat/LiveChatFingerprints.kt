package app.morphe.patches.youtube.livechat

import app.morphe.patcher.Fingerprint
import com.android.tools.smali.dexlib2.AccessFlags

internal const val LIVE_CHAT_RECYCLER_VIEW_DESCRIPTOR =
    "Lcom/google/android/libraries/youtube/livechat/ui/view/LiveChatRecyclerView;"
internal const val LIVE_CHAT_BANNER_DESCRIPTOR =
    "Lcom/google/android/libraries/youtube/livechat/ui/view/LiveChatBannerContainerLayout;"
internal const val LIVE_CHAT_SWIPEABLE_DESCRIPTOR =
    "Lcom/google/android/libraries/youtube/livechat/ui/view/LiveChatSwipeableContainerLayout;"

/**
 * `LiveChatRecyclerView` is the message list in live chat.
 */
internal object LiveChatRecyclerViewCtorFingerprint : Fingerprint(
    definingClass = LIVE_CHAT_RECYCLER_VIEW_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)

/**
 * `LiveChatBannerContainerLayout` holds live chat banners/alerts.
 */
internal object LiveChatBannerCtorFingerprint : Fingerprint(
    definingClass = LIVE_CHAT_BANNER_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)

/**
 * `LiveChatSwipeableContainerLayout` provides the swipeable container for chat.
 */
internal object LiveChatSwipeableCtorFingerprint : Fingerprint(
    definingClass = LIVE_CHAT_SWIPEABLE_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)