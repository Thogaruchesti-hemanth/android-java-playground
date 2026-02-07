# ImagePickerSaver - Public Image Organiser

A lightweight Android application that allows users to pick images from their gallery and save them to a public folder for easy organization and sharing.

## 📱 App Name

**ImagePickerSaver** - Your personal image organiser

## 🎯 Purpose

ImagePickerSaver simplifies image organisation by allowing users to:
- Pick images from their device gallery
- Automatically save selected images to a dedicated public folder
- Access saved images from any file manager or gallery app
- Organise images without cluttering the main gallery

## ✨ Features

- **Simple Image Picker**: Browse and select images from your device gallery
- **Public Folder Storage**: Saves images to `Pictures/MyPublicImages` for easy access
- **Automatic Naming**: Images are saved with timestamps to avoid conflicts
- **Modern Android**: Uses Scoped Storage (Android 10+) for secure file management
- **Edge-to-Edge UI**: Clean, modern interface with proper system bar handling

## 🏗️ Technical Details

### Architecture
- **Single Activity**: `MainActivity` - Main screen with image picker functionality
- **Modern Result API**: Uses `ActivityResultLauncher` instead of deprecated `onActivityResult`
- **Scoped Storage**: Implements `MediaStore` API for Android 10+ compatibility

### Key Components

1. **Image Picker**
   - Uses `ACTION_PICK` intent to access gallery
   - Registered with `ActivityResultContracts.StartActivityForResult()`

2. **Image Saver**
   - Uses `ContentResolver` and `MediaStore` APIs
   - Saves to public `Pictures/MyPublicImages` directory
   - Preserves image quality with stream-based copying

### File Structure
```
com.example.praticejava/
└── MainActivity.java          # Main activity with picker and saver logic
```

## 🚀 How It Works

1. **User Action**: Tap the "Pick Image" button
2. **Gallery Opens**: System gallery/photos app launches
3. **Image Selection**: User selects an image
4. **Automatic Save**: Selected image is copied to `Pictures/MyPublicImages`
5. **Confirmation**: Toast message confirms successful save

### Save Location
```
/storage/emulated/0/Pictures/MyPublicImages/
├── IMG_1704629184532.jpg
├── IMG_1704629245891.jpg
└── IMG_1704629367421.jpg
```

## 📋 Requirements

- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)
- **Permissions**: Read/Write media images (handled automatically via Scoped Storage)

## 🔧 Setup & Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   ```

2. **Open in Android Studio**
   - File → Open → Select project directory

3. **Build and Run**
   ```bash
   ./gradlew build
   ./gradlew installDebug
   ```

## 💡 Code Highlights

### Modern Activity Result API
```java
ActivityResultLauncher<Intent> imagePickerLauncher =
    registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        result -> {
            // Handle picked image
        }
    );
```

### Scoped Storage Implementation
```java
ContentValues values = new ContentValues();
values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/MyPublicImages");
Uri destUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
```

## 🎨 Customization

### Change Save Folder
Modify the `RELATIVE_PATH` in `saveImageToPublicFolder()`:
```java
values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/YourFolderName");
```

### Change File Naming Pattern
Update the `DISPLAY_NAME` format:
```java
values.put(MediaStore.Images.Media.DISPLAY_NAME, "MyImage_" + timestamp + ".jpg");
```

## 🔐 Permissions

The app uses **Scoped Storage**, which means:
- ✅ No explicit storage permissions needed for Android 10+
- ✅ Access to media files through MediaStore API
- ✅ User privacy maintained with restricted access

For Android 9 and below, add to `AndroidManifest.xml`:
```xml
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" 
                 android:maxSdkVersion="28" />
```

## 🐛 Known Limitations

- Only supports JPEG format (can be extended to PNG, WebP)
- Single image selection (no multi-select)
- No image preview before saving
- No option to choose custom save location

## 🚀 Future Enhancements

- [ ] Multi-image selection
- [ ] Image preview screen
- [ ] Custom folder selection
- [ ] Support for multiple image formats
- [ ] Image compression options
- [ ] Share functionality
- [ ] Delete saved images feature
- [ ] Grid view of saved images

## 📝 Use Cases

- **Backup Important Images**: Quickly save important photos to a dedicated folder
- **Image Organization**: Keep selected images separate from camera roll
- **Easy Sharing**: Organized folder makes sharing multiple images easier
- **Portfolio Creation**: Collect images for presentations or portfolios

## 🤝 Contributing

Feel free to fork this project and add new features. Suggestions welcome!

## 📄 License

This project is open source and available for educational purposes.

---

**Simple. Fast. Organized.** - ImagePickerSaver keeps your important images where you need them.
