# Independent Linux Runtime & Environment Setup Research

## 1. Overview
Mobile Dev Studio operates an independent Linux userspace using PRoot without requiring external terminal apps or root privileges.

---

## 2. PRoot Architecture
- Intercepts system calls via `ptrace(2)`.
- Fakes root privilege with `-0` flag.
- Mounts essential pseudo-filesystems (`/dev`, `/proc`, `/sys`) and shared storage (`/sdcard`).
- Unsets `LD_PRELOAD` to prevent Bionic libc collisions.

---

## 3. Minimal Bootstrap
- Alpine Linux ARM64 minirootfs (~3.2 MB compressed, ~12 MB extracted).
- Fast `apk` package manager for instant toolchain installation (Git, OpenJDK, Python, Node.js).
