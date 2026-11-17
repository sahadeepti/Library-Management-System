# Library Management System (Android Java, DAO, no database)

A starter Android Studio project that stores records locally in JSON files. It does not use SQLite, Room, MySQL or Firebase.

## Features
- Admin login (demo: `admin` / `admin123`)
- Book registration and searchable book list
- Student registration
- Issue books with automatically calculated due date
- Return books using the generated Issue ID
- Dashboard and borrowing reports
- DAO interfaces and implementations for books, students, issues and admin authentication

## Open and run
1. Extract the ZIP.
2. Open the `LibraryManagementSystem` folder in Android Studio.
3. Allow Gradle sync and install any requested Android SDK platform (compile SDK 35).
4. Run the `app` configuration on an emulator or Android device.

## Data storage
The app stores `books.json`, `students.json` and `issued_books.json` in the app's private internal files directory. These files are created automatically when records are saved. Admin demo credentials are hard-coded for this academic starter; replace them and implement secure password handling before real use.

## Important
- Data is local to the device and is lost if app data is cleared or the app is uninstalled. Add backup/export before relying on it.
- This is a compact academic starter project. The UI is intentionally simple and built programmatically in Java so it is easy to customize.
- For a real library, add transaction-safe file updates, backup/restore, stronger authentication, and multi-user synchronization.


## Fragment-based navigation UI

The updated version uses `MainActivity` as the post-login host activity and provides bottom navigation:
Home, Books, Students, Issue, and Reports.

- `HomeFragment` reads totals from the existing DAO implementations.
- The Books, Students, Issue, and Reports fragments open the existing feature Activities.
- Existing DAO classes, model classes, and JSON storage are retained.


Professional UI update: refreshed administrator login, dashboard cards, bottom navigation, and shared styling for form screens. Source code remains Java.
