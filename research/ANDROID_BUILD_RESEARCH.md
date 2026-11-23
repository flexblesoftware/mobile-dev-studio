# On-Device Android Build System Research

## 1. Overview
Compiling an Android application directly on an Android smartphone without external servers is a cornerstone capability of Mobile Dev Studio.

---

## 2. On-Device Build Pipeline
1. Resource Compilation: `aapt2 compile --dir app/src/main/res -o compiled_res.zip`
2. Resource Linking: `aapt2 link -I android.jar --manifest AndroidManifest.xml -o base.apk compiled_res.zip`
3. Bytecode Compilation: Java/Kotlin compiler translating source code to `.class` files.
4. Dexing: `d8 --min-api 26 --output dex/ classes/**/*.class` generating `classes.dex`.
5. Assembly & Insertion: Injecting `classes.dex` into `base.apk`.
6. Signing & Alignment: `apksigner sign --ks release.keystore base.apk`.
