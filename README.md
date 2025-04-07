# <h1 align="center"> 💵: ExpenseMate :dollar: </h1>

## Table of Contents

1.  [Overview](#overview)
2.  [Screenshots](#screenshots)
3.  [Features](#features)
4.  [Technologies Used](#technologies-used)
5.  [Prerequisites](#prerequisites)
6.  [Running the App](#how-to-run-the-app-locally)
7.  [Status](#status)
8.  [Further Development](#further-development)


## Overview

ExpenseMate is an Android application that allows users to track expenses among your friends, roommates, families or various social circles. It is build to handle sharing cost with your friends and groups. The app is built using Kotlin and Jetpack Compose, following modern Android development practices.

## Screenshots
<img src="https://github.com/user-attachments/assets/7e935883-820b-4d00-be6e-8be16f8e3bc9" width="220" height="500">
<img src="https://github.com/user-attachments/assets/358a717a-7b83-41d9-af84-abc2f8f3c27f" width="220" height="500">
<img src="https://github.com/user-attachments/assets/69b2ae3f-f4eb-4610-8077-518775de8509" width="220" height="500">
<p></p>
<img src="https://github.com/user-attachments/assets/09c92e0a-a3cd-4760-a353-083eba59ec33" width="220" height="500">
<img src="https://github.com/user-attachments/assets/670528f0-ad95-4619-8995-11a242a42475" width="220" height="500">
<img src="https://github.com/user-attachments/assets/22a491a9-85c0-4fba-a1e0-8fce162de4b8" width="220" height="500">


## Features
*   **Create Groups:** Can create group to divide expenses equally.
*   **Track Expenses:** Track expenses among friend group and split the cost.
*   **Track Activity:** Dashboard to track your expenses by category or monthly.
*   **Share Account:** Easily share your account using QR code feature.
*   **Modern UI:** A clean and intuitive user interface built with Jetpack Compose.
*   **Bottom navigation bar:** the user can navigate between screens with a bottom navigation bar.

## Technologies Used
[![Kotlin](https://img.shields.io/badge/Kotlin-%237F52FF.svg?logo=kotlin&logoColor=white)](#)
[![Android](https://img.shields.io/badge/Android-3DDC84?logo=android&logoColor=white)](#)

*   **Kotlin:** The primary programming language for Android development.
*   **Jetpack Compose:** A modern declarative UI toolkit for building Android interfaces.
*   **Material Design 3:** For creating a consistent and visually appealing UI.

## Prerequisites

*   **Android Studio:** Make sure you have the latest stable version of Android Studio installed. You can download it from [https://developer.android.com/studio](https://developer.android.com/studio).
*   **Android SDK:** Ensure that you have the necessary Android SDK components and build tools installed via the Android Studio SDK Manager. or
*   **Android Phone:** Android Phone works too
*   **Git:** To clone the project, you'll need Git installed.
*   **Emulator or device:** Make sure you have a emulator or a physical device to test the app.

## How to Run the App Locally

1.  **Clone the Repository:**
```
git clone https://github.com/shiddarthbista/ExpenseMate.git
```

2.  **Open in Android Studio:**

    *   Launch Android Studio.
    *   Click on "Open," and select the `ExpenseMate` directory that you just cloned.

3.  **Sync Project with Gradle Files:**

    *   Android Studio will automatically prompt you to sync the project with the Gradle files.
    *   If it doesn't, you can do it manually by clicking "File" > "Sync Project with Gradle Files."
    * This will download all the dependencies.

4.  **Create an Emulator or Connect a Device:**

    *   If you don't already have an Android Virtual Device (AVD) set up:
        *   Click on the AVD Manager icon (it looks like a phone with the Android logo).
        *   Follow the prompts to create a new virtual device. Choose a device configuration and an appropriate system image (API level).
    *   Alternatively, connect a physical Android device to your computer. Ensure that USB debugging is enabled on the device (in developer options).
    *   **QR Code:**
        *   Open the setting menu in the phone and search for `Pair device with QR code`.
        *   In Android Studio Select "Pair device using Wifi."
        *   Android Studio generate a QR code, which the phone can scan using its camera.
        *   Once scanned, the devices will be paired.
    *   **Pairing Number:**
        *   Open the connection menu in the app.
        *   Select "Connect via Pairing Number."
        *   Android Studio will generate a unique pairing number.
        *   Enter that number on your Android Phone.
        *   The devices will then be paired.

5.  **Run the App:**

    *   Click the "Run" button (the green play icon) in Android Studio.
    *   Select your emulator or connected device from the list.
    *   The app will be built and launched on the selected device.
  
    6. **Download APK and use on Android Device:**
         * Download the APK from [here]([https://github.com/shiddarthbista/PhotoPulse/tree/master/app/src/main/java/bista/shiddarth/photopulse/apk](https://github.com/shiddarthbista/ExpenseMate/blob/master/app/release/app-release.apk))
         * Run on your Android Device
      

## Status
 🚧 Base app complete - Exciting features coming soon 🚧

ExpenseMate is still under development and some screens are not yet implemented.

- [ ] Upload Bill photos
- [x] Sharing Feature
- [ ] Account Management
- [ ] Search for User
