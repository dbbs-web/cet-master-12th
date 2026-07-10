# CET Master 12th - Setup Guide

## Installation Steps

### 1. Sketchware Pro Import
```
1. Open Sketchware Pro
2. Click "Import Project"
3. Select the project file
4. Click "Import"
5. Update Firebase credentials
6. Build and run
```

### 2. Firebase Setup

#### Create Firebase Project
```
1. Go to https://console.firebase.google.com
2. Click "Create a project"
3. Enter project name: "cetmaster-12th"
4. Enable Google Analytics (optional)
5. Create project
```

#### Enable Authentication
```
1. Go to Authentication section
2. Click "Get Started"
3. Enable Email/Password provider
4. Set up user sign-up restrictions (optional)
```

#### Enable Realtime Database
```
1. Go to Realtime Database section
2. Click "Create Database"
3. Start in Test Mode (for development)
4. Choose database location
5. Update database rules with provided rules.json
```

#### Enable Storage
```
1. Go to Storage section
2. Click "Get Started"
3. Set up security rules
4. Create storage buckets for:
   - /notes/
   - /pdfs/
   - /avatars/
```

### 3. User Roles Setup

#### Create Admin User
```
1. Open Firebase Console
2. Create user with email: admin@cetmaster.com
3. Go to Realtime Database
4. Navigate to users/{uid}/role
5. Set value to "admin"
```

#### Create Regular Users
```
1. Use app registration feature
2. Automatically creates user role as "user"
```

### 4. Configure App Credentials

#### Update google-services.json
```json
{
  "apiKey": "YOUR_API_KEY",
  "authDomain": "YOUR_AUTH_DOMAIN",
  "projectId": "YOUR_PROJECT_ID",
  "storageBucket": "YOUR_STORAGE_BUCKET",
  "messagingSenderId": "YOUR_SENDER_ID",
  "appId": "YOUR_APP_ID"
}
```

### 5. Add Sample Content

#### Add Chapters
```
Database Path: /chapters
{
  "chapter1": {
    "subject": "Physics",
    "title": "Chapter 1: Introduction to Physics",
    "description": "Basic concepts of physics",
    "createdAt": timestamp
  }
}
```

#### Add MCQ Tests
```
Database Path: /mcqs/test1
{
  "title": "Physics Test 1",
  "subject": "Physics",
  "questions": {
    "q1": {
      "text": "What is SI unit of force?",
      "options": ["Joule", "Newton", "Pascal", "Watt"],
      "correctAnswer": 1
    }
  }
}
```

## Testing

### User Registration & Login
```
1. Launch app
2. Click "Register"
3. Fill form and register
4. Login with credentials
5. Verify dashboard opens
```

### Admin Functions
```
1. Login as admin
2. Go to Admin Panel
3. Upload test notes
4. Verify content appears in user dashboard
```

### MCQ Test
```
1. From dashboard, click subject
2. Select MCQ test
3. Answer questions within time limit
4. Submit and view score
```

## Troubleshooting

### Issue: "Firebase project not configured"
**Solution:**
- Verify google-services.json in correct location
- Rebuild project: `./gradlew clean build`

### Issue: "Authentication fails"
**Solution:**
- Check Firebase Email/Password provider is enabled
- Verify internet connection
- Check user exists in Firebase Auth

### Issue: "Can't access database"
**Solution:**
- Update database rules (set to test mode for development)
- Verify user is authenticated
- Check database rules in Firebase Console

## Deployment

### Release Build
```bash
./gradlew assembleRelease
```

### Sign APK
```bash
Path to keystore, keystore password, key alias, key password
```

### Upload to Play Store
1. Create Google Play Developer account
2. Create app listing
3. Upload signed APK
4. Complete store listing
5. Review and publish

## Support

For issues or questions:
- Check Firebase Documentation: https://firebase.google.com/docs
- Android Documentation: https://developer.android.com
- Sketchware Pro Documentation: https://sketchware.io
