# Changelog

## v0.1.0 (2026-11-22)

Initial standalone Morphe patch bundle for **YouTube `21.46.523`** (`com.google.android.youtube`, versionCode `1561314016`).

### Added

- `Disable Firebase Analytics collection`: disables `Laldk;->a()V` collection toggler and strips `AD_ID`, `AD_ID_NOTIFICATION`, `ACCESS_ADSERVICES_AD_ID`, `ACCESS_ADSERVICES_ATTRIBUTION` permissions from `AndroidManifest.xml`.
- `Bypass consent dialog delay`: skips OneTrust/GDPR consent WebView (`Lvdq;->onUserConsent`) initialization at startup.
- `Allow screenshots and screen recording`: clears `FLAG_SECURE` on player `SurfaceView` (`Laqbl;-><init>` calls `SurfaceView.setSecure(true)`).
- `Disable haptic feedback`: returns `false` from haptic feedback perform calls.
- `Force default playback speed`: hooks `PlaybackSpeedController` to persist speed preference.
- `Bypass forced app update`: returns early from `NewVersionAvailableActivity.onCreate` before `setContentView`.
- `Hide Shorts components`: hooks `ShortsController` to hide shelf/tab/button.

### Technical

- Built with morphe-patcher 2.4.0
- Compatible with Morphe Manager 2.6.0+
- Includes both v2/v3 and v3.1 certificate signatures
- Bundle includes `classes.dex` for Android compatibility via `buildAndroid`
- Custom Innertube endpoint documented via PPG enum (`http://127.0.0.1:8787`)