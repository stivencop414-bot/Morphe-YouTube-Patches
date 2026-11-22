# YouTube Patches (for Morphe)

Community patches for **YouTube** (`com.google.android.youtube`) built for **Morphe** (`morphe-patcher 2.4.0`), covering ad-component blocking, privacy hardening, forced-update bypass, screenshot/recording enablement and haptic control.

> **Scope:** These patches modify client-side behavior only. YouTube Premium entitlements, Widevine DRM, age gates, background playback and server-side features (including "Ask YouTube" / Gemini) are enforced by Google's backend and are not modified here.

## Supported Target

| App | Package Name | Version | Version Code | Certificate SHA-256 (v2/v3) | Certificate SHA-256 (v3.1) |
| --- | --- | --- | --- | --- | --- |
| YouTube | `com.google.android.youtube` | `21.46.523` | `1561314016` | `3d7a1223019aa39d9ea0e3436ab7c0896bfb4fb679f4de5fe7c23f326c8f994a` | `5aad2bee6db95d17e05a08d7d1e64c10a1511879154483916b6ae6c7fd9cb0c6` |

## Patches Included (`6`)

### Ads

| Patch | Default | Description |
| --- | --- | --- |
| `Alternative Hide Ads (clean-room)` | Enabled | Returns early from the constructors of the five ad UI components (`AdHighlightLayout`, `SkipAdButton`, `AdCountdownView`, `AdProgressView`, `AdDisclosureBannerView`) so they are never built. |

### Privacy

| Patch | Default | Description |
| --- | --- | --- |
| `Disable Firebase Analytics collection` | Enabled | Returns early from the analytics collection toggler `Laldk;->a()V` and strips `AD_ID`, `AD_ID_NOTIFICATION`, `ACCESS_ADSERVICES_AD_ID` and `ACCESS_ADSERVICES_ATTRIBUTION` from `AndroidManifest.xml`. |
| `Bypass consent dialog delay` | Disabled | Returns early from the consent WebView handler `Lvdq;->onUserConsent(Ljava/lang/String;)V`. |

### Interaction

| Patch | Default | Description |
| --- | --- | --- |
| `Allow screenshots and screen recording` | Enabled | Rewrites the constant feeding `SurfaceView.setSecure(true)` in `Laqbl;-><init>` to `0x0`. Hardware-backed Widevine L1 surfaces may still be restricted by Android codecs. |
| `Disable haptic feedback` | Disabled | Returns early from the shared haptic path `Lkae;->a()V`. |

### Misc

| Patch | Default | Description |
| --- | --- | --- |
| `Bypass forced app update` | Enabled | Returns early from `NewVersionAvailableActivity.onCreate(Landroid/os/Bundle;)V`, before its content view is created. |

## Verification

Every patch in this release was applied to the real `21.46.523` APK with
`morphe-desktop 2.3.0` and the resulting bytecode was confirmed after
`apktool d -f`:

```text
Applied: Allow screenshots and screen recording
Applied: Alternative Hide Ads (clean-room)
Applied: Bypass consent dialog delay
Applied: Bypass forced app update
Applied: Disable Firebase Analytics collection
Applied: Disable haptic feedback
```

The patched APK was signed with APK Signature Scheme v2 and reported
self-consistent by `rea inspect-apk-signature`.

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

1. Sources → `+` → paste this repository URL
2. Select your `com.google.android.youtube` APK/APKM
3. Enable the desired patches and patch

### Desktop (morphe-desktop 2.3.0)

```bash
java -jar morphe-desktop-2.3.0-all.jar \
  patch --patches=patches-0.1.0.mpp \
  --validate -o out.apk input.apk
```

## Custom endpoint (development)

The `Laleq` enum exposes a `PPG` entry pointing at `http://127.0.0.1:8787`, which is
useful for pointing Innertube traffic at a local proxy while testing. See
`patches/src/main/kotlin/app/morphe/patches/youtube/misc/CustomEndpointDocs.kt`.
This only redirects the base Innertube host; Google-side AI endpoints use separate
authenticated channels and are not affected.

## License

Licensed under the [GNU General Public License v3.0](LICENSE) with Additional Terms under Section 7 described in [NOTICE](NOTICE).

## Disclaimer

Not affiliated with Google, YouTube or Morphe. Patches are provided for educational
and interoperability purposes. Use at your own risk.