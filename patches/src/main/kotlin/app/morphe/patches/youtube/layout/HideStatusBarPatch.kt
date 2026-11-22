package app.morphe.patches.youtube.layout

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.patch
import app.morphe.patches.shared.compat.AppCompatibilities
import com.android.tools.smali.dexlib2.AccessFlags

internal const val SLIM_STATUS_BAR_DESCRIPTOR = "Lcom/google/android/libraries/youtube/slimstatusbar/SlimStatusBar;"

/**
 * `SlimStatusBar` is the in-app status bar header rendered above the watch/feed UI.
 *
 * Observed in 21.46.523: a `LinearLayout` subclass with four constructors and
 * `a(I)` / `b(CharSequence)` setters plus `onFinishInflate()`.
 *
 * Hiding is done by rewriting the view's visibility rather than removing the class,
 * because other UI code holds a reference to it.
 */
internal object SlimStatusBarInflateFingerprint : Fingerprint(
    definingClass = SLIM_STATUS_BAR_DESCRIPTOR,
    name = "onFinishInflate",
    accessFlags = listOf(AccessFlags.PROTECTED, AccessFlags.FINAL),
    returnType = "V",
    parameters = emptyList(),
)

@Suppress("unused")
val hideStatusBarPatch = patch(
    name = "Hide in-app status bar",
    description = "Collapses the `SlimStatusBar` header so the in-app status bar takes no vertical space.",
    default = false,
) {
    category("Layout")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        // `View.GONE` on the component itself; the view stays attached so callers
        // that hold a reference keep working.
        SlimStatusBarInflateFingerprint.method.addInstructions(
            0,
            """
                const/16 v0, 0x8
                invoke-virtual {p0, v0}, Lcom/google/android/libraries/youtube/slimstatusbar/SlimStatusBar;->setVisibility(I)V
            """.trimIndent(),
        )
    }
}