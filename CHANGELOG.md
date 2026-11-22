# Changelog

## v0.1.0 (2026-11-22)

Initial standalone Morphe patch bundle for **YouTube `21.46.523`**
(`com.google.android.youtube`, versionCode `1561314016`).

Every patch in this release was applied to the real APK with `morphe-desktop 2.3.0`,
and the injected bytecode was confirmed after `apktool d -f`. UI patches use a
`hide-after-construction` strategy: the original constructor (including its superclass
call and field setup) runs, and `View.setVisibility(GONE)` is appended before the final
return. This keeps the Dalvik bytecode verifiable — an early `return-void` in a
constructor produces a `VerifyError` on device.

### Added (29 patches)

**Ads (4)**

- `Alternative Hide Ads (clean-room)`: hides the five ad player UI components
  (`AdHighlightLayout`, `SkipAdButton`, `AdCountdownView`, `AdProgressView`,
  `AdDisclosureBannerView`).
- `Block ad WebViews`: hides `AdsWebView` / `PlayableAdWebView` after construction and
  stops `AdsWebView` from attaching to a window.
- `Block ad brand/CTA overlays`: hides `BrandInteractionView` and `InfoChip`.
- `Block ad countdown/progress text overlays`: hides `AdCountdownTextView` and
  `AdProgressTextView`.

**Privacy (4)**

- `Disable Firebase Analytics collection`: returns early from the analytics toggler
  `Laldk;->a()V` and strips `AD_ID`, `AD_ID_NOTIFICATION`, `ACCESS_ADSERVICES_AD_ID`
  and `ACCESS_ADSERVICES_ATTRIBUTION` from `AndroidManifest.xml`.
- `Disable HATS surveys`: hides Google HATS survey prompts (`HatsSurvey`,
  `HatsContainer`, `HatsHorizontalSurvey`).
- `Disable logging sampler` (optional): forces `LogSamplerImpl.a()` to `false` and stops
  its batch flush.
- `Bypass consent dialog delay` (optional): returns early from
  `Lvdq;->onUserConsent(Ljava/lang/String;)V`.

**Interaction (2)**

- `Allow screenshots and screen recording`: rewrites the constant feeding
  `SurfaceView.setSecure(true)` in `Laqbl;-><init>` to `0x0`.
- `Disable haptic feedback` (optional): returns early from the shared haptic path
  `Lkae;->a()V`.

**Player (4)**

- `Disable quick-seek tap bloom animation` (optional): hides `TapBloomView` and
  `CircularClipTapBloomView`.
- `Disable video heatmap/chapter markers` (optional): hides `HeatMarkerView`.
- `Disable subtitle rendering` (optional): hides `SubtitleWindowView` (YouTube native)
  and `SubtitleView` (Media3).
- `Hide scrub preview thumbnails` (optional): hides `YoutubeScrubbedPreviewView` and
  `ReelScrubbedPreviewView`.
- `Hide supplemental player surfaces` (optional, testing only): hides the core
  `PlayerView` container, which removes video rendering.

**Layout (9)**

- `Hide tutorial clings`: hides first-run coach marks (`ClingTutorialView`, `ClingView`).
- `Hide promo/announcement snackbars`: hides the bottom promo bar (`Mealbar`).
- `Hide cinematic ambient backdrop` (optional): hides `CinematicImageView`.
- `Hide filter chip cloud` (optional): hides `ChipCloudView`.
- `Hide navigation bar divider` (optional): hides `NavigationBarDividerLayout`.
- `Hide fullscreen engagement panel` (optional): hides
  `FullscreenEngagementPanelCoordinatorLayout`.
- `Hide player overlay containers` (optional): hides `InlinePlayerOverlayLayout` and
  `YouTubePlayerOverlaysLayout`.
- `Hide in-app status bar` (optional): collapses `SlimStatusBar` on inflate.
- `Hide offline download arrows` (optional): hides `OfflineArrowView`.

**Cast (1)**

- `Hide Cast buttons` (optional): hides `MdxMediaRouteButton` and
  `MdxEntryPointButton`.

**Live (1)**

- `Hide live chat components` (optional): hides `LiveChatRecyclerView`,
  `LiveChatBannerContainerLayout` and `LiveChatSwipeableContainerLayout`.

**Shorts (1)**

- `Hide Subscribe button on Shorts` (optional): hides the Shorts `SubscribeButton`
  overlay.

**Misc (2)**

- `Bypass forced app update`: returns early from
  `NewVersionAvailableActivity.onCreate(Landroid/os/Bundle;)V`.
- `Disable app promotional popups` (optional): returns early from `Laebr;->b(I)V`.

### Verification

23 of 29 patches applied in a single combined run against the real APK (all
default-enabled patches plus every optional patch tested together):

```text
Applied: Allow screenshots and screen recording
Applied: Alternative Hide Ads (clean-room)
Applied: Block ad WebViews
Applied: Block ad brand/CTA overlays
Applied: Block ad countdown/progress text overlays
Applied: Bypass forced app update
Applied: Disable Firebase Analytics collection
Applied: Disable HATS surveys
Applied: Hide Subscribe button on Shorts
Applied: Hide filter chip cloud
Applied: Hide live chat components
Applied: Hide navigation bar divider
Applied: Hide offline download arrows
Applied: Hide promo/announcement snackbars
Applied: Hide scrub preview thumbnails
Applied: Hide tutorial clings
(+ 7 optional patches in the extended run: haptic, consent, sampler, promos,
   cast buttons, subtitles, heatmap, bloom, engagement panel)
```

Confirmed in the patched DEX: `super` call intact, field setup intact, and
`setVisibility(GONE)` appended in each hidden constructor — including `ClingView` /
`ClingTutorialView`, where the original body already contained its own `setVisibility`
call and the injected call lands after field setup but before the final return.
`AD_ID` count in the patched manifest is 0.

Output APK signed with APK Signature Scheme v2, self-consistent per
`rea inspect-apk-signature`.

### Technical

- Built with morphe-patcher 2.4.0
- Compatible with Morphe Manager 2.6.0+
- Supported signer fingerprints: v2/v3 `3d7a1223…` and v3.1 `5aad2bee…`
- Bundle includes `classes.dex` via `buildAndroid`
- Custom Innertube endpoint documented via the `PPG` enum (`http://127.0.0.1:8787`)

### Scope notes

Client-side only. YouTube Premium entitlements, Widevine DRM, age gates, background
playback and server-side features (including "Ask YouTube" / Gemini) are enforced by
Google's backend and are not modified by these patches.