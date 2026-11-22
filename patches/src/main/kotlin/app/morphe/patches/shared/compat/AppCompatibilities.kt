package app.morphe.patches.shared.compat

import app.morphe.patcher.patch.AppTarget
import app.morphe.patcher.patch.Compatibility

internal object AppCompatibilities {
    const val YOUTUBE_NAME = "YouTube"
    const val YOUTUBE_PACKAGE = "com.google.android.youtube"
    const val YOUTUBE_COLOR = 0xFF0000
    // v2/v3 certificate SHA-256
    const val YOUTUBE_SIGNER_SHA256 = "3d7a1223019aa39d9ea0e3436ab7c0896bfb4fb679f4de5fe7c23f326c8f994a"
    // v3.1 certificate SHA-256 (for rotation)
    const val YOUTUBE_SIGNER_SHA256_V31 = "5aad2bee6db95d17e05a08d7d1e64c10a1511879154483916b6ae6c7fd9cb0c6"
    const val YOUTUBE_21_46_VERSION_CODE = 1561314016

    fun youtube(): Array<Compatibility> = arrayOf(
        Compatibility(
            name = YOUTUBE_NAME,
            packageName = YOUTUBE_PACKAGE,
            appIconColor = YOUTUBE_COLOR,
            signatures = setOf(YOUTUBE_SIGNER_SHA256, YOUTUBE_SIGNER_SHA256_V31),
            targets = listOf(
                AppTarget(version = "21.46.523", versionCode = YOUTUBE_21_46_VERSION_CODE),
            ),
        ),
    )
}