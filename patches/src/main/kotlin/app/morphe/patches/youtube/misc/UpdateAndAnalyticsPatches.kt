package app.morphe.patches.youtube.misc

import app.morphe.patcher.patch.document
import app.morphe.patcher.patch.patch
import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.util.returnEarly
import org.w3c.dom.Element

private const val AD_ID_PERMISSION = "com.google.android.gms.permission.AD_ID"
private const val AD_ID_NOTIFICATION_PERMISSION = "com.google.android.gms.permission.AD_ID_NOTIFICATION"
private const val ADSERVICES_AD_ID_PERMISSION = "android.permission.ACCESS_ADSERVICES_AD_ID"
private const val ADSERVICES_ATTRIBUTION_PERMISSION = "android.permission.ACCESS_ADSERVICES_ATTRIBUTION"

@Suppress("unused")
val disableFirebaseAnalyticsPatch = patch(
    name = "Disable Firebase Analytics collection",
    description = "Stops YouTube from enabling Firebase Analytics collection and strips the Advertising ID " +
        "permissions (`AD_ID`, `AD_ID_NOTIFICATION`, `ACCESS_ADSERVICES_AD_ID`, " +
        "`ACCESS_ADSERVICES_ATTRIBUTION`) from `AndroidManifest.xml`.",
    default = true,
) {
    category("Privacy")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        // `Laldk;->a()V` is the only setter of the collection flag; disabling it
        // keeps the app at its initial, collection-disabled state.
        FirebaseCollectionToggleFingerprint.method.returnEarly()

        document("AndroidManifest.xml").use { xml ->
            val manifest = xml.documentElement
            val permissions = manifest.getElementsByTagName("uses-permission")
            val toRemove = mutableListOf<Element>()
            for (i in 0 until permissions.length) {
                val elem = permissions.item(i) as? Element ?: continue
                if (elem.getAttribute("android:name") in setOf(
                        AD_ID_PERMISSION,
                        AD_ID_NOTIFICATION_PERMISSION,
                        ADSERVICES_AD_ID_PERMISSION,
                        ADSERVICES_ATTRIBUTION_PERMISSION,
                    )
                ) {
                    toRemove += elem
                }
            }
            toRemove.forEach { manifest.removeChild(it) }
        }
    }
}

@Suppress("unused")
val bypassForcedUpdatePatch = patch(
    name = "Bypass forced app update",
    description = "Prevents `NewVersionAvailableActivity` from presenting the forced-upgrade screen by " +
        "returning before its content view is created.",
    default = true,
) {
    category("Misc")
    compatibleWith(*AppCompatibilities.youtube())

    execute {
        NewVersionActivityOnCreateFingerprint.method.returnEarly()
    }
}