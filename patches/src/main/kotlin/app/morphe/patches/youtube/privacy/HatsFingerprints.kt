package app.morphe.patches.youtube.privacy

import app.morphe.patcher.Fingerprint
import com.android.tools.smali.dexlib2.AccessFlags

internal const val HATS_SURVEY_DESCRIPTOR =
    "Lcom/google/android/apps/youtube/app/common/ui/bottomui/HatsSurvey;"
internal const val HATS_CONTAINER_DESCRIPTOR =
    "Lcom/google/android/apps/youtube/app/common/ui/bottomui/HatsContainer;"
internal const val HATS_HORIZONTAL_SURVEY_DESCRIPTOR =
    "Lcom/google/android/apps/youtube/app/common/ui/bottomui/HatsHorizontalSurvey;"

/**
 * `HatsSurvey` is a Google HATS (Human Ads Testing System) survey prompt.
 */
internal object HatsSurveyCtorFingerprint : Fingerprint(
    definingClass = HATS_SURVEY_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)

/**
 * `HatsContainer` hosts the HATS survey.
 */
internal object HatsContainerCtorFingerprint : Fingerprint(
    definingClass = HATS_CONTAINER_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)

/**
 * `HatsHorizontalSurvey` is the horizontal-layout HATS survey.
 */
internal object HatsHorizontalSurveyCtorFingerprint : Fingerprint(
    definingClass = HATS_HORIZONTAL_SURVEY_DESCRIPTOR,
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.CONSTRUCTOR),
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
)