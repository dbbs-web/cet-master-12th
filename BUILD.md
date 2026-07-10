# CET Master 12th - Build Documentation

## Building the Project

### Prerequisites
- Android SDK 21 or higher
- Java 8 or higher
- Gradle 7.4.2 or higher

### Debug Build
```bash
./gradlew assembleDebug
```

### Release Build
```bash
./gradlew assembleRelease
```

### Running Tests
```bash
./gradlew test
```

### Running on Emulator
```bash
./gradlew installDebug
```

## Firebase Configuration

1. Create a new Firebase project at https://console.firebase.google.com
2. Add Android app to your project
3. Download `google-services.json`
4. Place it in `swb-project/` directory
5. Update Firebase credentials in build.gradle

## Project Structure

```
cet-master-12th/
├── swb-project/
│   ├── src/              # Java source files
│   ├── res/              # Resources (layouts, drawables, etc)
│   ├── project.json      # Sketchware project config
│   └── AndroidManifest.xml
├── firebase-config/      # Firebase configuration files
├── build.gradle          # App build configuration
└── README.md             # Project documentation
```

## Key Features Implemented

- ✅ User Authentication (Firebase Auth)
- ✅ Admin Panel for content management
- ✅ Chapter-wise notes for 4 subjects
- ✅ MCQ tests with timer
- ✅ User profile and statistics
- ✅ Offline caching support
- ✅ Dark mode support
- ✅ Material Design 3 UI

## Troubleshooting

### Build Error: Could not find com.google.gms:google-services
Solution: Update Google Services plugin in build.gradle

### Firebase Authentication Not Working
Solution: Verify Firebase credentials and ensure internet permission is set

### RecyclerView Items Not Showing
Solution: Ensure adapter is properly initialized and data is loaded from Firebase
