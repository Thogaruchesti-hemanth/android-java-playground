# Multi-Auth Provider Android Application

A robust Android authentication system implementing multiple sign-in providers with clean architecture principles using Firebase Authentication.

## 📋 Features

- **Email/Password Authentication**
  - User registration with email and password
  - Secure login functionality
  - Firebase Authentication backend

- **Google Sign-In**
  - One-tap Google authentication
  - Modern Credential Manager API integration
  - Seamless Firebase integration

- **GitHub OAuth**
  - GitHub account authentication
  - OAuth 2.0 flow implementation
  - Automatic token handling

- **Extensible Architecture**
  - Ready for additional providers (Facebook, Twitter, etc.)
  - Clean separation of concerns
  - Easy to maintain and extend

## 🏗️ Project Structure

```
com.example.praticejava/
├── activities/          # UI layer - Activities and Fragments
├── data/
│   └── auth/           # Data layer - Firebase implementation
├── domain/
│   └── auth/           # Domain layer - Interfaces and business logic
└── utils/              # Utility classes and helpers
```

### Architecture Pattern

This project follows **Clean Architecture** principles with clear separation between layers:

- **Activities Layer**: Handles UI and user interactions
- **Domain Layer**: Contains business logic and authentication contracts
- **Data Layer**: Implements authentication logic using Firebase
- **Utils Layer**: Provides reusable utility functions

## 🚀 Getting Started

### Prerequisites

- Android Studio Arctic Fox or later
- Minimum SDK: 24 (Android 7.0)
- Target SDK: 34 (Android 14)
- Java 8 or higher
- Firebase project setup

### Firebase Setup

1. **Create a Firebase Project**
   - Go to [Firebase Console](https://console.firebase.google.com/)
   - Create a new project or use existing one
   - Add your Android app to the Firebase project

2. **Download Configuration File**
   - Download `google-services.json`
   - Place it in the `app/` directory of your project

3. **Enable Authentication Methods**
   - Navigate to Authentication → Sign-in method
   - Enable the following providers:
     - ✅ Email/Password
     - ✅ Google
     - ✅ GitHub

### Google Sign-In Configuration

1. **Configure OAuth Consent Screen**
   - Go to Google Cloud Console
   - Set up OAuth consent screen
   - Add necessary scopes

2. **Create OAuth Credentials**
   - Navigate to Credentials
   - Create OAuth 2.0 Client ID (Web application type)
   - Copy the Web Client ID to your Firebase project

3. **Add SHA-1 Fingerprint**
   ```bash
   keytool -list -v -keystore ~/.android/debug.keystore -alias androiddebugkey -storepass android -keypass android
   ```
   - Copy SHA-1 fingerprint
   - Add it to Firebase Project Settings → Your apps

### GitHub OAuth Configuration

1. **Create GitHub OAuth App**
   - Go to GitHub Settings → Developer settings → OAuth Apps
   - Click "New OAuth App"
   - Fill in the details:
     - Application name: Your app name
     - Homepage URL: Your website or Firebase URL
     - Authorization callback URL: Copy from Firebase Console

2. **Configure in Firebase**
   - Copy Client ID and Client Secret from GitHub
   - Paste them in Firebase Console → Authentication → GitHub provider

## 📦 Installation

1. **Clone the repository**
   ```bash
   git clone <your-repository-url>
   cd praticejava
   ```

2. **Add Firebase Configuration**
   - Place `google-services.json` in the `app/` directory

3. **Add Dependencies**
   
   Ensure your `build.gradle` files include:

   **Project-level `build.gradle`:**
   ```gradle
   buildscript {
       dependencies {
           classpath 'com.google.gms:google-services:4.4.0'
       }
   }
   ```

   **App-level `build.gradle`:**
   ```gradle
   plugins {
       id 'com.android.application'
       id 'com.google.gms.google-services'
   }

   dependencies {
       // Firebase
       implementation platform('com.google.firebase:firebase-bom:32.7.0')
       implementation 'com.google.firebase:firebase-auth'
       
       // Google Sign-In
       implementation 'com.google.android.gms:play-services-auth:20.7.0'
       implementation 'androidx.credentials:credentials:1.2.0'
       implementation 'androidx.credentials:credentials-play-services-auth:1.2.0'
       implementation 'com.google.android.libraries.identity.googleid:googleid:1.1.0'
       
       // Other dependencies
       implementation 'androidx.appcompat:appcompat:1.6.1'
       implementation 'com.google.android.material:material:1.11.0'
   }
   ```

4. **Sync and Build**
   ```bash
   ./gradlew build
   ```

## 💻 Usage

### Email/Password Authentication

```java
AuthManager authManager = new FirebaseAuthManager();

// Sign Up
authManager.signupWithEmail(email, password, new OnAuthCompleteListener() {
    @Override
    public void onSuccess(FirebaseUser user) {
        // Handle successful registration
    }

    @Override
    public void onFailure(Exception exception) {
        // Handle error
    }
});

// Sign In
authManager.loginWithEmail(email, password, listener);
```

### Google Sign-In

```java
authManager.signInWithGoogle(activity, new OnAuthCompleteListener() {
    @Override
    public void onSuccess(FirebaseUser user) {
        // User signed in successfully
    }

    @Override
    public void onFailure(Exception exception) {
        // Handle authentication error
    }
});
```

### GitHub Sign-In

```java
authManager.signInWithGitHub(activity, new OnAuthCompleteListener() {
    @Override
    public void onSuccess(FirebaseUser user) {
        // User authenticated with GitHub
    }

    @Override
    public void onFailure(Exception exception) {
        // Handle error
    }
});
```

## 🔧 Key Components

### AuthManager Interface
Defines the contract for all authentication operations, ensuring consistent implementation across different providers.

### FirebaseAuthManager
Concrete implementation of `AuthManager` using Firebase Authentication SDK. Handles all authentication logic and provider integrations.

### SocialLoginUtils
Utility class managing modern Google Sign-In flow using Credential Manager API, eliminating the need for `onActivityResult`.

### OnAuthCompleteListener
Callback interface providing success and failure states for authentication operations.

## 🎨 Customization

### Adding New Authentication Providers

1. **Implement the method in AuthManager interface**
2. **Add implementation in FirebaseAuthManager**
3. **Enable the provider in Firebase Console**
4. **Configure provider-specific credentials**

Example for Facebook:
```java
@Override
public void signInWithFacebook(Activity activity, OnAuthCompleteListener listener) {
    // Implement Facebook authentication logic
}
```

## 🔐 Security Best Practices

- Never commit `google-services.json` with sensitive data to public repositories
- Use ProGuard/R8 for code obfuscation in production
- Implement proper error handling and user feedback
- Validate user input before authentication
- Use HTTPS for all network communications
- Implement session management and token refresh

## 🐛 Troubleshooting

### Common Issues

**Google Sign-In fails with error 10:**
- Ensure SHA-1 fingerprint is added to Firebase Console
- Verify Web Client ID is correctly configured
- Check that Google Sign-In is enabled in Firebase Console

**GitHub OAuth redirect fails:**
- Verify callback URL matches exactly in GitHub OAuth app settings
- Ensure GitHub provider is enabled in Firebase

**Build errors:**
- Run `./gradlew clean build`
- Invalidate caches and restart Android Studio
- Ensure all dependencies are properly synced

## 📝 License

This project is available for educational and commercial use. Please review the license file for details.

## 🤝 Contributing

Contributions are welcome! To add new authentication providers or improve existing functionality:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-provider`)
3. Commit your changes (`git commit -m 'Add new auth provider'`)
4. Push to the branch (`git push origin feature/new-provider`)
5. Open a Pull Request

## 📧 Contact

For questions, issues, or feature requests, please open an issue in the repository or contact the maintainer.

## 🙏 Acknowledgments

- Firebase Authentication for robust backend services
- Google Identity Services for modern authentication
- GitHub OAuth for developer-friendly integration
