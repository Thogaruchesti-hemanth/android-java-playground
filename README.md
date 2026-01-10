# ☕ Android Java Playground

[![Java](https://img.shields.io/badge/Java-8+-orange.svg)](https://www.oracle.com/java/)
[![Android](https://img.shields.io/badge/Android-API%2021+-brightgreen.svg)](https://developer.android.com)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](CONTRIBUTING.md)

> A hands-on Java Android learning repository where each branch contains a unique implementation. Practice, explore, and master Android development through practical examples - one branch at a time.

## 📖 About This Project

This repository is designed to help developers learn Android development with Java through **practical, real-world implementations**. Each branch focuses on a specific pattern, component, or concept, making it easy to:

- 🎯 Focus on one concept at a time
- 📱 See working implementations
- 🔄 Compare different approaches
- 💡 Learn best practices
- 🚀 Build production-ready UIs

## 🌟 Why Branch-Based Learning?

Traditional tutorials often mix multiple concepts, making it hard to focus. This repository uses a **branch-per-concept** approach:

- **Isolated Learning**: Each branch contains one complete UI example
- **No Clutter**: Focus on what matters without distractions
- **Easy Navigation**: Switch branches to explore different UIs
- **Version Control**: Track your progress and experiments
- **Reusable Code**: Copy and adapt what you need

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or later
- JDK 8 or higher
- Basic knowledge of Java
- Familiarity with Android development concepts

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/Thogaruchesti-hemanth/android-java-playground.git
   cd android-java-playground
   ```

2. **View available branches**
   ```bash
   git branch -a
   ```

3. **Switch to a specific UI branch**
   ```bash
   git checkout branch-name
   ```

4. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned directory
   - Wait for Gradle sync to complete

5. **Run the app**
   - Connect your device or start an emulator
   - Click the "Run" button or press `Shift + F10`

## 🎯 Available UI Branches

Each branch is prefixed with the proper name  for easy identification. Here's a sample of what you'll find:
```master
├── feature/
|   └── widgets/
|   |   ├──basic-widget
|   |   └──clickable-widget
|   └── edge-to-edge-layout
|   ├── modern-splash
├── onboarding/                       
│   ├── dot-indicators/
│   │   └── animated-dot-onboarding
```

> 📝 See [BRANCH_INDEX.md](BRANCH_INDEX.md) for the complete list with screenshots and learning objectives.

## 📚 How to Use This Repository

### For Beginners

1. Start with branches marked as **Beginner**
2. Read the branch's README for learning objectives
3. Run the app and interact with the UI
4. Study the code comments and structure
5. Try modifying layouts, colours, and strings
6. Experiment and break things (that's learning!)

### For Intermediate Learners

1. Pick a branch that interests you
2. Understand the overall architecture
3. Study Activity/Fragment lifecycle
4. Learn RecyclerView patterns
5. Try implementing variations
6. Combine concepts from multiple branches

### For Advanced Developers

1. Use as a reference for complex patterns
2. Study architecture implementations (MVVM, MVP)
3. Learn performance optimisation techniques
4. Contribute your own UI implementations
5. Help review and improve existing branches

**Recommended Order:**
1. Basic Activities and XML layouts
2. Common Views and Widgets (Button, TextView, EditText)
3. RecyclerView and Adapters
4. Fragments and Navigation
5. SharedPreferences and SQLite
6. Networking with Retrofit
7. Architecture patterns (MVVM, MVP)
8. Advanced topics (Services, Broadcast Receivers)

## 🤝 Contributing

We welcome contributions! Here's how you can help:

### Adding a New Branch

1. **Fork the repository**
2. **Create a new branch** from `main`
   ```bash
   git checkout main
   git pull origin main
   git checkout -b ui/your-ui-name
   ```
3. **Implement your UI** with:
   - Clean, readable Java code
   - XML layouts following Material Design
   - Comments explaining key concepts
   - A dedicated README in the branch
   - Screenshots or GIFs
4. **Submit a Pull Request** with:
   - Clear description of the UI
   - Learning objectives
   - Difficulty level
   - Screenshots

### Contribution Guidelines

- ✅ One UI concept per branch
- ✅ Follow Material Design principles
- ✅ Include comprehensive comments
- ✅ Add a README with screenshots
- ✅ Test on multiple screen sizes
- ✅ Use meaningful variable names
- ✅ Follow Java coding conventions
- ✅ Proper resource organisation (strings.xml, colors.xml, dimens.xml)

## 📸 Screenshots

> Each branch contains its own screenshots showing the implemented UI

| Edge-to-Edge Layout                                                                                                              | Dot Indicators Onboarding                                                           | Moder-splah Code                                                        | Basic widget                                                      | Clickable Widgets                                                      |
| --------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------ | --------------------------------------------------------------- | ------------------------------------------------------------- | ------------------------------------------------------------- |
|<a href="https://github.com/Thogaruchesti-hemanth/android-java-playground/tree/feature/edge-to-edge-ui"> <img src="https://github.com/user-attachments/assets/0bf476cd-8612-4d1d-937b-ceda4e5b7ef5" width="180" height="380"/></a> |<a href="https://github.com/Thogaruchesti-hemanth/android-java-playground/tree/onboarding/dot-indicators/animated-dot-onboarding"><img src="https://github.com/user-attachments/assets/ec5f4fc1-f5cc-4862-8bbe-1e7e5f4d6e59" width="180" height="380"/></a> | <a href="https://github.com/Thogaruchesti-hemanth/android-java-playground/tree/feature/modern-splash"><img src="https://github.com/user-attachments/assets/41106153-11c2-4795-8a9a-cd08a3792461" width="180" height="380"/></a> | <a href="https://github.com/Thogaruchesti-hemanth/android-java-playground/tree/features/widgets/basic-widget" width="180" height="380"/> <img src="https://github.com/user-attachments/assets/7397b531-6fb0-4238-a31f-050d1d2f3384" width="180" height="380"/> </a> | <a href="https://github.com/Thogaruchesti-hemanth/android-java-playground/tree/features/widgets/clickable-widget"> <img src="https://github.com/user-attachments/assets/7350ae56-8cef-4343-8f6b-19cdf9339934" width="180" height="380"/>|

| Screen 6                                                      | Screen 7                                                      | Screen 8                                                      | Screen 9                                                      | Screen 10                                                      |
| ------------------------------------------------------------- | ------------------------------------------------------------- | ------------------------------------------------------------- | ------------------------------------------------------------- | -------------------------------------------------------------- |
| <img src="screenshots/screen6.png" width="180" height="380"/> | <img src="screenshots/screen7.png" width="180" height="380"/> | <img src="screenshots/screen8.png" width="180" height="380"/> | <img src="screenshots/screen9.png" width="180" height="380"/> | <img src="screenshots/screen10.png" width="180" height="380"/> |

| Screen 11                                                      | Screen 12                                                      | Screen 13                                                      | Screen 14                                                      | Screen 15                                                      |
| -------------------------------------------------------------- | -------------------------------------------------------------- | -------------------------------------------------------------- | -------------------------------------------------------------- | -------------------------------------------------------------- |
| <img src="screenshots/screen11.png" width="180" height="380"/> | <img src="screenshots/screen12.png" width="180" height="380"/> | <img src="screenshots/screen13.png" width="180" height="380"/> | <img src="screenshots/screen14.png" width="180" height="380"/> | <img src="screenshots/screen15.png" width="180" height="380"/> |

| Screen 16                                                      | Screen 17                                                      | Screen 18                                                      | Screen 19                                                      | Screen 20                                                      |
| -------------------------------------------------------------- | -------------------------------------------------------------- | -------------------------------------------------------------- | -------------------------------------------------------------- | -------------------------------------------------------------- |
| <img src="screenshots/screen16.png" width="180" height="380"/> | <img src="screenshots/screen17.png" width="180" height="380"/> | <img src="screenshots/screen18.png" width="180" height="380"/> | <img src="screenshots/screen19.png" width="180" height="380"/> | <img src="screenshots/screen20.png" width="180" height="380"/> |



## 🛠️ Tech Stack
- **Language**: Java
- **UI Framework**: Android Views & XML Layouts
- **Architecture**: MVVM, MVP (where applicable)
- **Database**: Room, SQLite
- **Networking**: Retrofit, OkHttp
- **Image Loading**: Glide, Picasso
- **Dependency Injection**: Dagger (where applicable)
- **Design System**: Material Design Components
- **Build System**: Gradle

## 📖 Resources

### Official Documentation
- [Android Developer Docs](https://developer.android.com)
- [Android Java Fundamentals](https://developer.android.com/courses/fundamentals-training/overview-v2)
- [Material Design Components](https://material.io/develop/android)

### Community Resources
- [Android Developers YouTube](https://www.youtube.com/c/AndroidDevelopers)
- [Vogella Android Tutorials](https://www.vogella.com/tutorials/android.html)
- [r/androiddev](https://www.reddit.com/r/androiddev/)
- [Stack Overflow - Android](https://stackoverflow.com/questions/tagged/android)

### Key Topics Covered

**Beginner Level:**
- Activities and Intents
- XML Layouts (LinearLayout, RelativeLayout, ConstraintLayout)
- Basic Views (TextView, Button, EditText, ImageView)
- Event Handling (onClick, onTouch)
- Toast and Snackbar messages
- ListView and RecyclerView basics

**Intermediate Level:**
- Fragments and Fragment Manager
- ViewPager and TabLayout
- Custom Views and Custom Attributes
- Animation (Property Animator, View Animator)
- SharedPreferences
- SQLite and Room Database
- Material Design Components
- RecyclerView advanced patterns

**Advanced Level:**
- MVVM and MVP Architecture
- LiveData and ViewModel
- Retrofit and API integration
- Dependency Injection with Dagger
- Services and Broadcast Receivers
- Background Tasks (AsyncTask, WorkManager)
- Performance Optimisation
- Memory Management

## 💬 Community & Support

- **Issues**: Found a bug? [Open an issue](https://github.com/Thogaruchesti-hemanth/android-java-playground/issues)
- **Discussions**: Questions? [Start a discussion](https://github.com/Thogaruchesti-hemanth/android-java-playground/discussions)
- **Instagram**: Follow [@indian_mobile_developer](https://www.instagram.com/indian_mobile_developer) for updates

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## ⭐ Show Your Support

If this repository helped you learn Android development with Java, please:
- ⭐ Star this repository
- 🐦 Share it on social media
- 🤝 Contribute your own UI implementations
- 📝 Write about your learning experience
- 💬 Recommend it to fellow developers

## 🙏 Acknowledgments

- Thanks to all contributors who help make this resource better
- Inspired by the amazing Android developer community
- Built with ❤️ for learners worldwide
- Special thanks to the Java Android developers keeping the ecosystem strong

## 🔗 Related Projects

- **[Compose UI Playground](https://github.com/Thogaruchesti-hemanth/compose-ui-playground)** - Learn Jetpack Compose with the same branch-based approach
- More coming soon...

## 📊 Project Statistics

![GitHub stars](https://img.shields.io/github/stars/Thogaruchesti-hemanth/android-java-playground?style=social)
![GitHub forks](https://img.shields.io/github/forks/Thogaruchesti-hemanth/android-java-playground?style=social)
![GitHub watchers](https://img.shields.io/github/watchers/Thogaruchesti-hemanth/android-java-playground?style=social)

## 🎯 Roadmap

- [ ] 20+ UI implementations covering all skill levels
- [ ] Video tutorials for each branch
- [ ] Sample apps combining multiple concepts
- [ ] Kotlin migration guides
- [ ] Testing examples (JUnit, Espresso)
- [ ] CI/CD integration examples

---

<div align="center">

**Happy Android Development! 🚀**

Made with ❤️ by [Thogaruchesti Hemanth](https://github.com/Thogaruchesti-hemanth)

[⬆ Back to Top](#-android-java-playground)

</div>
