# CET Master 12th - Feature Documentation

## User Features

### 1. Authentication
- **Email/Password Registration**: Create new account with validation
- **Email/Password Login**: Secure login using Firebase
- **Session Management**: Automatic logout on inactivity
- **Password Recovery**: Email-based password reset

### 2. Dashboard
- **Subject Cards**: 4 subjects (Physics, Chemistry, Math, Biology)
- **Quick Access**: Direct links to Notes, MCQ, Papers
- **User Profile**: Display user name and email
- **Navigation**: Bottom tab navigation for easy access

### 3. Study Materials
- **Chapter Notes**: Organized by subject and chapter
- **PDF Viewer**: View PDFs with page navigation
- **Formula Sheets**: Quick reference formulas
- **Previous Year Papers**: Past exam papers with solutions

### 4. Practice Tests
- **MCQ Tests**: Multiple choice questions with timer
- **Auto-submission**: Submit when time runs out
- **Instant Feedback**: See results immediately
- **Explanation**: View answer explanations

### 5. User Statistics
- **Tests Taken**: Total number of tests completed
- **Accuracy**: Overall accuracy percentage
- **Notes Read**: Count of materials viewed
- **Performance**: Subject-wise performance tracking

### 6. Personalization
- **Dark Mode**: Easy on eyes theme
- **Notifications**: Push notifications for updates
- **Bookmarks**: Save favorite materials
- **Downloads**: Download for offline access

## Admin Features

### 1. Content Management
- **Upload Notes**: Add chapter-wise study materials
- **Upload PDFs**: Upload question papers and solutions
- **Upload MCQs**: Create and manage MCQ tests
- **Edit Content**: Update existing materials
- **Delete Content**: Remove obsolete materials

### 2. User Management
- **View Users**: List all registered users
- **User Status**: Track user activity
- **Disable Users**: Deactivate user accounts
- **View Statistics**: User engagement metrics

### 3. Announcements
- **Post Announcements**: Send notifications to all users
- **Schedule Posts**: Schedule announcements
- **Edit/Delete**: Manage announcements

### 4. Analytics
- **Usage Statistics**: Track app usage
- **Popular Content**: Most accessed materials
- **User Engagement**: Active user metrics
- **Performance Reports**: Overall system performance

## Technical Features

### 1. Offline Support
- **Caching**: Automatic content caching
- **Offline Mode**: Works without internet
- **Sync**: Automatic sync when online
- **Cache Management**: Manual cache clearing

### 2. Security
- **Firebase Authentication**: Secure user authentication
- **Role-based Access**: Admin and user roles
- **Database Rules**: Fine-grained access control
- **Data Encryption**: Encrypted data transmission

### 3. Performance
- **Lazy Loading**: Load content on demand
- **Image Optimization**: Compressed images
- **Database Indexing**: Optimized queries
- **Caching Strategy**: Efficient caching

### 4. User Experience
- **Material Design 3**: Modern UI/UX
- **Smooth Animations**: Transitions and effects
- **Responsive Layout**: Works on all screen sizes
- **Accessibility**: Support for accessibility features

## API Endpoints (Firebase)

### Authentication
```
POST /auth/register
POST /auth/login
POST /auth/logout
POST /auth/resetPassword
```

### Users
```
GET  /users/{uid}
POST /users/{uid}
PUT  /users/{uid}
DELETE /users/{uid}
```

### Chapters
```
GET  /chapters
GET  /chapters/{chapterId}
POST /chapters (Admin)
PUT  /chapters/{chapterId} (Admin)
DELETE /chapters/{chapterId} (Admin)
```

### MCQs
```
GET  /mcqs
GET  /mcqs/{testId}
POST /mcqs (Admin)
PUT  /mcqs/{testId} (Admin)
DELETE /mcqs/{testId} (Admin)
```

### Statistics
```
GET  /statistics/{uid}
POST /statistics/{uid}/update
```

### Announcements
```
GET  /announcements
POST /announcements (Admin)
PUT  /announcements/{id} (Admin)
DELETE /announcements/{id} (Admin)
```
