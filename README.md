# CET Master 12th - Complete Project Setup

## Project Overview

**CET Master 12th** is a comprehensive Android application designed for students preparing for competitive entrance exams. The app provides study materials, practice tests, and performance tracking in a user-friendly interface.

### Key Highlights
- ✅ **Multi-subject support**: Physics, Chemistry, Mathematics, Biology
- ✅ **Comprehensive features**: Notes, PDFs, MCQ tests, Previous papers
- ✅ **Admin management**: Content uploads, user management, announcements
- ✅ **Firebase integration**: Real-time database, authentication, storage
- ✅ **Offline support**: Download and use content without internet
- ✅ **Modern UI**: Material Design 3 with dark mode support

## Project Structure

```
cet-master-12th/
├── swb-project/
│   ├── src/                      # Java source files
│   │   ├── SplashActivity.java
│   │   ├── UserLoginActivity.java
│   │   ├── UserRegisterActivity.java
│   │   ├── AdminLoginActivity.java
│   │   ├── UserDashboardActivity.java
│   │   ├── ChapterNotesActivity.java
│   │   ├── MCQTestActivity.java
│   │   ├── ProfileActivity.java
│   │   ├── AdminPanelActivity.java
│   │   ├── ChapterViewHolder.java
│   │   ├── AdminPagerAdapter.java
│   │   ├── AdminUploadNotesFragment.java
│   │   ├── AdminUploadPDFFragment.java
│   │   ├── AdminUploadMCQFragment.java
│   │   ├── AdminManageUsersFragment.java
│   │   └── AdminAnnouncementsFragment.java
│   ├── res/
│   │   ├── layout/               # XML layout files
│   │   ├── drawable/             # Drawable resources
│   │   ├── anim/                 # Animation files
│   │   ├── values/
│   │   │   ├── colors.xml
│   │   │   ├── styles.xml
│   │   │   ├── strings.xml
│   │   │   ├── arrays.xml
│   │   │   ├── dimens.xml
│   │   │   └── drawables.xml
│   │   └── xml/
│   │       └── app_preferences.xml
│   ├── AndroidManifest.xml
│   ├── view.json
│   ├── logic.json
│   ├── event.json
│   └── activity.json
├── firebase-config/
│   ├── google-services.json
│   ├── firebase-service-account.json
│   └── database-rules.json
├── build.gradle                  # App build configuration
├── build.gradle.kts              # Project build configuration
├── gradle.properties
├── proguard-rules.pro
├── BUILD.md                      # Build instructions
├── SETUP.md                      # Setup guide
├── FEATURES.md                   # Feature documentation
└── README.md                     # Project documentation
```

## Installation & Setup

### Prerequisites
- Android SDK 21 or higher
- Java 8 or higher
- Gradle 7.4.2 or higher
- Firebase project

### Quick Start

1. **Clone the repository**
   ```bash
   git clone https://github.com/dbbs-web/cet-master-12th.git
   cd cet-master-12th
   ```

2. **Configure Firebase**
   - Download `google-services.json` from Firebase Console
   - Place it in `swb-project/` directory

3. **Build the project**
   ```bash
   ./gradlew clean build
   ```

4. **Run on device/emulator**
   ```bash
   ./gradlew installDebug
   ```

## Features

### User Features
- **Authentication**: Secure login/registration with Firebase
- **Dashboard**: Quick access to subjects and features
- **Study Materials**: Chapter notes, PDFs, formulas
- **Practice Tests**: MCQ tests with timers and scoring
- **Performance Tracking**: Statistics and progress monitoring
- **Personalization**: Dark mode, notifications, preferences

### Admin Features
- **Content Management**: Upload notes, PDFs, MCQ tests
- **User Management**: View and manage user accounts
- **Announcements**: Post updates and notifications
- **Analytics**: Track app usage and user engagement

## Technology Stack

- **Frontend**: Android (Java)
- **UI Framework**: Material Design 3
- **Backend**: Firebase Realtime Database
- **Authentication**: Firebase Authentication
- **Storage**: Firebase Cloud Storage
- **Push Notifications**: Firebase Cloud Messaging

## API Integration

### Firebase Realtime Database Structure
```
users/
  {uid}/
    name, email, phone, role, createdAt, preferences

chapters/
  {chapterId}/
    subject, title, description, notesUrl, createdAt

mcqs/
  {testId}/
    title, subject, questions[], duration

statistics/
  {uid}/
    testsTaken, accuracy, notesRead, timestamp

announcements/
  {announcementId}/
    title, message, timestamp, postedBy
```

## Configuration

### Environment Variables
```
FIREBASE_API_KEY=<your-api-key>
FIREBASE_PROJECT_ID=cetmaster-12th
FIREBASE_AUTH_DOMAIN=cetmaster-12th.firebaseapp.com
```

### Database Rules
Rules are provided in `firebase-config/database-rules.json`

## Testing

### Run Tests
```bash
./gradlew test
```

### Test Coverage
```bash
./gradlew jacocoTestReport
```

## Build & Release

### Debug Build
```bash
./gradlew assembleDebug
```

### Release Build
```bash
./gradlew assembleRelease
```

## Troubleshooting

### Build Issues
- Clear Gradle cache: `./gradlew clean`
- Invalidate Android Studio cache
- Update SDK tools

### Firebase Issues
- Verify `google-services.json` location
- Check Firebase project configuration
- Enable required APIs in Firebase Console

### Runtime Issues
- Check internet connection
- Verify Firebase credentials
- Check database rules
- Review logcat for errors

## Documentation

- [BUILD.md](BUILD.md) - Build instructions
- [SETUP.md](SETUP.md) - Setup and configuration guide
- [FEATURES.md](FEATURES.md) - Feature documentation
- [Android Documentation](https://developer.android.com)
- [Firebase Documentation](https://firebase.google.com/docs)

## Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open Pull Request

## Version History

- **v1.0.0** (Current) - Initial release
  - User authentication
  - Dashboard and navigation
  - Study materials
  - MCQ tests
  - Admin panel

## Roadmap

- [ ] Video tutorials
- [ ] Live classes integration
- [ ] Discussion forum
- [ ] Advanced analytics
- [ ] Gamification (badges, leaderboards)
- [ ] Multi-language support
- [ ] iOS version

## License

This project is licensed under the MIT License - see LICENSE file for details

## Support

For issues and support:
- Create an issue on GitHub
- Email: support@cetmaster.com
- Documentation: https://cetmaster.com/docs

## Credits

**Developer**: DBBS Web
**Contact**: dbbs54102@gmail.com
**GitHub**: https://github.com/dbbs-web

## Acknowledgments

- Firebase for backend services
- Material Design for UI guidelines
- Android community for support and resources

---

**Last Updated**: July 10, 2026
**Status**: Active Development ✅
