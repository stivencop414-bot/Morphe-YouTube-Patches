package app.morphe.patches.youtube.privacy

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.patch.patch
import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.util.returnEarly
import com.android.tools.smali.dexlib2.AccessFlags

internal const val LOG_SAMPLER_DESCRIPTOR = "Lcom/google/android/libraries/youtube/logging/sampling/LogSamplerImpl;"

/**
 * `LogSamplerImpl.a()Z` reports whether YouTube logging sampling is enabled.
 *
 * Observed in 21.46.523: reads the instance field `a:Z` and returns it. Forcing it
 * to `false` is the documented on/off switch for the sampler; the sibling methods
 * `b(I)` / `c(IJ)` then return a no-op sampler.
 */
internal object LogSamplerEnabledFingerprint : Fingerprint(
    definingClass = LOG_SAMPLER_DESCRIPTOR,
    name = "a",
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "Z",
    parameters = emptyList(),
)

/**
 * `LogSamplerImpl.d()V` flushes sampled log batches.
 */
internal object LogSamplerFlushFingerprint : Fingerprint(
    definingClass = LOG_SAMPLER_DESCRIPTOR,
    name = "d",
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "V",
    parameters = emptyList(),
)

@Suppress("unused")
val disableLoggingSamplerPatch = patch(
    name = "Disable logging sampler",
    description = "Forces YouTube's logging sampler off and stops its batch flush path, reducing background " +
        "telemetry batching write traffic.",
    default = false,
) {
    category("Privacy")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        LogSamplerEnabledFingerprint.method.returnEarly(false)
        LogSamplerFlushFingerprint.method.returnEarly()
    }
}