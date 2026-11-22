# Changelog

## v0.1.0 (2026-11-22)

Initial standalone Morphe patch bundle for **YouTube `21.46.523`** (`com.google.android.youtube`, versionCode `1561314016`).

All patches in this release were validated by applying them to the real APK with
`morphe-desktop 2.3.0`, then confirming the injected bytecode with `apktool d -f`.

### Added

**Ads**

- `Alternative Hide Ads (clean-room)`: returns early from the constructors of the five ad UI components (`AdHighlightLayout`, `SkipAdButton`, `AdCountdownView`, `AdProgressView`, `AdDisclosureBannerView`) so they are never built.

**Privacy**

- `Disable Firebase Analytics collection`: returns early from the analytics collection toggler `Laldk;->a()V`, and strips `AD_ID`, `AD_ID_NOTIFICATION`, `ACCESS_ADSERVICES_AD_ID` and `ACCESS_ADSERVICES_ATTRIBUTION` from `AndroidManifest.xml`.
- `Bypass consent dialog delay` (optional, disabled by default): returns early from the consent WebView handler `Lvdq;->onUserConsent(Ljava/lang/String;)V`.

**Interaction**

- `Allow screenshots and screen recording`: rewrites the `const/4` feeding `SurfaceView.setSecure(true)` in `Laqbl;-><init>` to `0x0`.
- `Disable haptic feedback` (optional, disabled by default): returns early from the shared haptic path `Lkae;->a()V`.

**Misc**

- `Bypass forced app update`: returns early from `NewVersionAvailableActivity.onCreate(Landroid/os/Bundle;)V`.

### Verification

Applied to the real APK and confirmed in the patched DEX/manifest:

| Patch | Confirmed change |
| --- | --- |
| Alternative Hide Ads | `SkipAdButton.<init>` starts with `return-void` |
| Screenshots | `const/16 p1, 0x0` before `SurfaceView->setSecure(Z)V` |
| Firebase Analytics | `aldk.a()` starts with `return-void`; `AD_ID` count in manifest = 0 |
| Consent dialog | `vdq.onUserConsent` starts with `return-void` |
| Haptic feedback | `kae.a()` starts with `return-void` |
| Forced update | `NewVersionAvailableActivity.onCreate` starts with `return-void` |

Output APK was signed with APK Signature Scheme v2 and reported self-consistent by
`rea inspect-apk-signature`.

### Technical

- Built with morphe-patcher 2.4.0
- Compatible with Morphe Manager 2.6.0+
- Supported signer fingerprints: v2/v3 `3d7a1223…` and v3.1 `5aad2bee…`
- Bundle includes `classes.dex` for Android compatibility via `buildAndroid`
- Custom Innertube endpoint documented via the `PPG` enum (`http://127.0.0.1:8787`)

### Scope notes

Client-side only. YouTube Premium entitlements, Widevine DRM, age gates, background
playback and server-side features (including "Ask YouTube" / Gemini) are enforced by
Google's backend and are not modified by these patches.