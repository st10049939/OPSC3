SmartNotes is a simple and secure Android note-taking application developed using Kotlin in Android Studio.
It allows users to create, edit, and manage personal notes both online and offline, with automatic synchronization when the device reconnects to the internet.

The app includes modern mobile features such as user authentication, single sign-on (SSO), offline data storage, REST API integration, real-time notifications, and multi-language support (English and isiZulu).

SmartNotes was created as part of a practical assignment (POE) to demonstrate an understanding of mobile app development concepts, backend integration, and user experience design.


 Features

User Registration and Login

Users can register with an email and password.

Passwords are securely encrypted using Firebase Authentication.

Existing users can log in or use Google SSO for quick access.

Settings Screen

Users can update preferences such as theme (light/dark mode) and language.

Settings are stored locally using SharedPreferences.

REST API Connection

The app connects to a cloud-hosted database using Retrofit (RESTful API).

CRUD operations (Create, Read, Update, Delete) allow users to manage notes online.

Data is synchronized with Firebase Firestore or a custom Node.js API.

Offline Mode with Sync

Using RoomDB, notes can be created and stored offline.

When the device reconnects, WorkManager automatically syncs offline notes to the online database.

Real-Time Notifications

Implemented via Firebase Cloud Messaging (FCM).

Users receive alerts when notes are synced, updated, or when new data is available.

Multi-Language Support

Supports English and isiZulu through localized string resources.

Users can switch languages from the settings screen, and the app updates dynamically.

User-Friendly Interface

Clean and minimal layout designed with Material Design guidelines.

Easy navigation through the toolbar and floating action buttons.

 Installation and Setup

Clone or download the project folder from the repository.

Open the project in Android Studio (latest version).

Sync Gradle files to install dependencies.

Add your Firebase configuration file (google-services.json) under app/ directory.

Connect your Android device or emulator.

Click Run to build and launch the app.

 Technologies Used

Kotlin (Android)

Firebase Authentication (Login & SSO)

Room Database (Offline data storage)

Retrofit + REST API (Cloud synchronization)

Firebase Cloud Messaging (FCM) (Notifications)

WorkManager (Background synchronization)

SharedPreferences (Settings management)

📱 How to Use

Launch the app.

Register a new account or sign in with Google.

Create, view, or edit notes using the add/edit buttons.

Switch off the internet and add a note (offline mode).

Reconnect to sync all offline notes automatically.

Change the app’s language or theme in the settings menu.

Observe push notifications when updates occur.

Learning Outcomes

The development of SmartNotes demonstrated practical use of:

Secure authentication and SSO integration.

REST API communication and database synchronization.

Offline-first app architecture using RoomDB.

Implementation of multi-language support for South African users.

Managing background tasks and push notifications in Android.

Author

Name: [Zama Ndlovu]
Course: [DISD 3]

This project was developed for educational purposes. All assets and code are original or sourced from open-source frameworks under permissible licenses.
