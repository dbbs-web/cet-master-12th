# CET Master 12th - Android Education App

एक व्यापक शैक्षणिक Android अ्यॅप जो 12वीच्या विद्यार्थ्यांसाठी तयार केले आहे।

## 📱 Features

### 👤 User Panel
- **Dashboard**: सर्व विषयांचा overview
- **Subjects**: Physics, Chemistry, Mathematics, Biology
- **Chapter-wise Notes**: प्रत्येक अध्याय साठी विस्तृत नोट्स
- **PDF Viewer**: नोट्स आणि प्रश्नपत्रिका पाहण्यासाठी
- **Previous Year Papers**: मागील वर्षांचे प्रश्नपत्रिका
- **MCQ Tests**: टाइमर सह व्यायाम
- **Formula Sheets**: महत्वाचे गणितीय सूत्र
- **Search**: संपूर्ण कंटेंटमध्ये शोध
- **Bookmarks**: महत्वाचे आयटम सेव करा
- **Downloads**: ऑफलाइन access
- **Progress Tracking**: अभ्यास प्रगती
- **Profile & Settings**: यूजर प्रबंधन
- **Dark Mode**: रात्रीचा mode
- **Notifications**: महत्वाचे updates
- **Offline Caching**: Internet विना काम

### 👨‍💼 Admin Panel
- **Upload Notes**: विषय आणि अध्याय अनुसार
- **Upload PDFs**: प्रश्नपत्रिका आणि नोट्स
- **Upload MCQs**: प्रश्नांसह उत्तरे
- **Manage Users**: यूजर activity monitor
- **Edit/Delete Content**: कंटेंट प्रबंधन
- **Announcements**: महत्वाचे घोषणा

## 🎨 Design & Technology

- **UI**: Material Design 3 (Blue & White theme)
- **Architecture**: MVVM + Repository Pattern
- **Database**: Firebase Realtime Database
- **Storage**: Firebase Cloud Storage
- **Authentication**: Firebase Auth (User & Admin)
- **Components**: RecyclerView, CardView, BottomNavigation
- **Animations**: Smooth transitions & loading indicators
- **Responsive**: सर्व डिवाइस साठी

## 🛠️ Tech Stack

- **Language**: Java (Android)
- **IDE**: Sketchware Pro
- **Backend**: Firebase
- **UI Framework**: Material Design 3
- **Database**: Firebase Realtime Database + Storage

## 📦 Project Structure

```
cet-master-12th/
├── swb-project/
│   ├── project.json          # App metadata & config
│   ├── view.json             # UI screens
│   ├── logic.json            # Business logic
│   ├── event.json            # Event handlers
│   ├── activity.json         # Activities
│   ├── drawable/             # Icons & resources
│   ├── assets/               # Images & media
│   ├── data/                 # Sample data
│   └── strings.xml           # String resources
├── firebase-config/
│   ├── google-services.json  # Firebase config
│   └── firebase-rules.json   # Database rules
├── documentation/
│   ├── SETUP.md              # Setup guide
│   ├── SCREENS.md            # Screen details
│   └── API.md                # API documentation
└── README.md
```

## 🚀 Installation & Usage

### Sketchware Pro मध्ये Import करा:
1. Sketchware Pro खोला
2. "Import Project" निवड करा
3. `cet-master-12th.swb` फाईल निवड करा
4. Firebase config जोडा
5. Build & Run करा

## 📋 Screens Overview

| Screen | Purpose | Components |
|--------|---------|------------|
| Splash | App startup | Animated logo, loading |
| Login | User authentication | Email, password, Firebase |
| Register | New user signup | Form validation, Firebase |
| Dashboard | Main interface | Bottom nav, subject cards |
| Notes | Chapter content | RecyclerView, PDF viewer |
| MCQ | Practice tests | Timer, scoring, submit |
| Progress | Learning stats | Charts, graphs |
| Settings | User preferences | Dark mode, notifications |
| Admin Panel | Content management | Upload, edit, delete |

## 🔐 Firebase Setup

```json
{
  "apiKey": "YOUR_API_KEY",
  "authDomain": "your-project.firebaseapp.com",
  "projectId": "your-project",
  "storageBucket": "your-project.appspot.com",
  "messagingSenderId": "YOUR_SENDER_ID",
  "appId": "YOUR_APP_ID"
}
```

## 📝 License

Open source for educational purposes

## 👨‍💻 Developer

Created with ❤️ by DBBS Web Team
