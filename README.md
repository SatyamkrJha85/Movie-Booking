# 🎬 Movie Booking App

A modern movie booking application built with **Jetpack Compose**. The app allows users to browse movies, view details, and switch between dark and light themes.

## 🚀 Features

- **Movie List**: View a list of now-playing movies fetched from an external API.
- **Movie Details**: Detailed information about each movie, including description, ratings, and more.
- **Dark Mode**: Toggle between dark and light themes for a better user experience.
- **Navigation**: Easily navigate between the home screen and movie details using Jetpack Compose's navigation components.
- **State Management**: Handle UI state changes seamlessly with Jetpack Compose and `remember` for smooth user experience.

## 🛠️ Tech Stack

- **Jetpack Compose**: Modern UI toolkit for building native Android interfaces.
- **Kotlin Coroutines**: For handling asynchronous tasks.

## 📱 Screenshots

| Dark Mode |
|------------|
![Newz](https://github.com/user-attachments/assets/f62bc43a-0130-46cb-afd1-d10d62a95fe5)



## 📱 Video



https://github.com/user-attachments/assets/20c7b456-48a5-470d-875f-f1da76e540c1



## 🎨 Theming

The app supports both **Light** and **Dark** themes. Users can toggle between these modes, and the system UI colors such as status bar and navigation bar adjust accordingly.

- **Dark Mode Toggle**: A user-friendly switch to change between light and dark themes.
  
```kotlin
DarkModeToggle(
    isDarkTheme = isDarkMode,
    onToggleChange = { isDarkMode = !isDarkMode }
)

git clone https://github.com/SatyamkrJha85/Movie-Booking.git
cd movie-booking-app

