# Shared Logic - Kotlin Multiplatform Project

This project is a **Kotlin Multiplatform (KMP)** module containing shared business logic and data models, designed to be used across Android, iOS, and other supported platforms.

## 🧱 Project Structure
```
shared/
├── src/
│   ├── commonMain/          # Shared code across all platforms
│   ├── commonTest/          # Shared tests
│   ├── androidMain/         # (Optional) Android-specific code
│   └── iosMain/             # (Optional) iOS-specific code
├── build.gradle.kts         # KMP module build config
```

> This project currently focuses **only on shared logic**, keeping platform-specific code to a minimum or none.

## 🛠 Tech Stack

- **Kotlin Multiplatform**
- **Kotlinx Coroutines** for async programming
- **Kotlinx Serialization** (optional)
- **Ktor Client** (optional for networking)

## 🚀 Getting Started

### Including in Your Project

```kotlin
dependencies {
    implementation(project(":shared"))
}
```

### Build & Test
To build the shared module:

```bash
./gradlew build
```

## Features
- Pure Kotlin shared logic
- Reusable across multiple platforms
