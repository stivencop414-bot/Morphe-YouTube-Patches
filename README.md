# YouTube Patches (for Morphe)

Community patches for **YouTube** (`com.google.android.youtube`) built for **Morphe** (`morphe-patcher 2.4.0`), covering privacy, forced-update bypass, screenshot/recording enablement, and UI customization.

> **Note on server-enforced features:** These patches modify client-side behavior only. YouTube Premium entitlements, Widevine L1 DRM, age-gated content, background playback, and server-side recommendations (e.g. "Ask YouTube" / Gemini) are enforced server-side and cannot be bypassed by client patches.

## Supported Target

| App | Package Name | Version | Version Code | Certificate SHA-256 (v2/v3) | Certificate SHA-256 (v3.1) |
| --- | --- | --- | --- | --- | --- |
| YouTube | `com.google.android.youtube` | `21.46.523` | `1561314016` | `3d7a1223019aa39d9ea0e3436ab7c0896bfb4fb679f4de5fe7c23f326c8f994a` | `5aad2bee6db95d17e05a08d7d1e64c10a1511879154483916b6ae6c7fd9cb0c6` |

## Patches Included (`7`)

### Privacy

| Patch | Default | Description |
| --- | --- | --- |
| `Disable Firebase Analytics collection` | Enabled | Stops Firebase Analytics collection at startup and strips `AD_ID`, `AD_ID_NOTIFICATION`, `ACCESS_ADSERVICES_AD_ID`, `ACCESS_ADSERVICES_ATTRIBUTION` permissions from `AndroidManifest.xml`. |
| `Bypass consent dialog delay` | Disabled | Skips OneTrust/GDPR consent WebView initialization at startup; dismisses the blocking prompt immediately. |

### Interaction

| Patch | Default | Description |
| --- | --- | --- |
| `Allow screenshots and screen recording` | Enabled | Clears `FLAG_SECURE` on the player `SurfaceView` so screenshots and screen recording work. Hardware-backed Widevine L1 DRM surfaces may still be restricted by Android codecs. |
| `Disable haptic feedback` | Disabled | Disables all haptic feedback (vibration) in the app. |
| `Force default playback speed` | Disabled | Forces a default playback speed instead of resetting to 1.0× on each video. |

### Misc

| Patch | Default | Description |
| --- | --- | --- |
| `Bypass forced app update` | Enabled | Prevents `NewVersionAvailableActivity` from presenting the forced-upgrade screen by returning before its content view is created. |

### Layout

| Patch | Default | Description |
| --- | --- | --- |
| `Hide Shorts components` | Disabled | Hides Shorts shelf, tab, and creation button from the UI. |

### Development / Custom Endpoint

| Patch | Default | Description |
| --- | --- | --- |
| `Custom Innertube endpoint (docs)` | N/A | Documents how to use a custom Innertube API endpoint via the existing `PPG` enum value (`http://127.0.0.1:8787`). Run a local proxy on that port, or modify via ADB. Full Settings UI requires a Morphe extension (not included). See `patches/src/main/kotlin/app/morphe/patches/youtube/misc/CustomEndpointDocs.kt`. |

## Building

Requires JDK 17+.

```bash
./gradlew :patches:buildAndroid :patches:generatePatchesList --no-daemon -Dorg.gradle.jvmargs="-Xmx1280m" -PallowMavenLocal=true
```

Outputs:

- Bundle: `patches/build/libs/patches-0.1.0.mpp`
- Catalog: `patches-list.json`

## Testing

### Android (Morphe Manager 2.6.0+)
1. Sources → + → paste this repository URL
2. Select your `com.google.android.youtube` APK/APKM
3. Enable desired patches and patch

### Desktop (morphe-desktop-2.3.0-all.jar)
```bash
java -jar morphe-desktop-2.3.0-all.jar patch --validate patches-0.1.0.mpp
java -jar morphe-desktop-2.3.0-all.jar patch patches-0.1.0.mpp input.apk output.apk
```

## License

Licensed under the [GNU General Public License v3.0](LICENSE) with Additional Terms under Section 7 described in [NOTICE](NOTICE).

## Disclaimer

This project is not affiliated with Google, YouTube, or Morphe. Patches are provided for educational and interoperability purposes only. Use at your own risk.