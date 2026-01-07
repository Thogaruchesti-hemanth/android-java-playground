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

Each branch is prefixed with `ui/` for easy identification. Here's a sample of what you'll find:

| Branch Name | Difficulty | Description |
|------------|-----------|-------------|
| `ui/login-activity` | Beginner | Login screen with validation |
| `ui/recyclerview-basic` | Beginner | Simple RecyclerView implementation |
| `ui/fragments-navigation` | Beginner | Fragment navigation basics |
| `ui/viewpager-tabs` | Intermediate | ViewPager with TabLayout |
| `ui/custom-view` | Intermediate | Creating custom views |
| `ui/room-database` | Intermediate | Room database with UI |
| `ui/mvvm-architecture` | Advanced | Complete MVVM implementation |
| `ui/retrofit-api` | Advanced | API integration with Retrofit |

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

| Login Activity | RecyclerView | Fragment Navigation |
|-------------|--------------|-----------------|
| ![Login](screenshots/login.png) | ![RecyclerView](screenshots/recyclerview.png) | ![Fragments](screenshots/fragments.png) |

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
