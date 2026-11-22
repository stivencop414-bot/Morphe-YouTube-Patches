# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.1.0] - 2026-11-22

### Added
- Initial release for YouTube 21.46.523 (versionCode 1561314016)
- **Privacy**: Disable Firebase Analytics collection + strip AD_ID permissions
- **Privacy**: Bypass consent dialog delay (OneTrust/GDPR)
- **Interaction**: Allow screenshots and screen recording (clear FLAG_SECURE)
- **Interaction**: Disable haptic feedback
- **Interaction**: Force default playback speed
- **Misc**: Bypass forced app update (NewVersionAvailableActivity)
- **Layout**: Hide Shorts components (shelf, tab, creation button)
- **Dev**: Documentation for custom Innertube endpoint via PPG enum (localhost:8787)

### Technical
- Built with morphe-patcher 2.4.0
- Compatible with Morphe Manager 2.6.0+
- Includes both v2/v3 and v3.1 certificate signatures
- Bundle includes classes.dex for Android compatibility