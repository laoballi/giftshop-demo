# هامرگالری — اپلیکیشن اندروید

اپ فروشگاه هامرگالری (WebView حرفه‌ای با اسپلش انیمیشنی لوگو).

## ساخت APK (خودکار)
هر push به main → GitHub Actions → artifact به نام `HamerGallery-APK` (app-release.apk امضاشده).

## انتشار در مارکت‌ها
- **کافه‌بازار / مایکت:** فایل APK artifact را دانلود و در پنل توسعه‌دهنده آپلود کنید (نیاز به احراز هویت مدارکی).
- **Google Play:** با همین APK در Google Play Console (اکانت ۲۵$ یک‌بار).
- **دانلود مستقیم:** APK را روی سایت بگذارید و لینک «دانلود اپ» بدهید.

## تنظیمات
- URL سایت: `MainActivity.java → URL`
- نام/آیکون: `AndroidManifest.xml` + `res/mipmap/`
- نسخه: `app/build.gradle → versionCode/versionName`
