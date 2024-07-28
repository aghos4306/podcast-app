# Podcast Android App
## Overview
Podcast app is an Android application that displays a list of podcasts with detailed information about episodes on each podcasts. Users can browse the list of podcasts, view detailed information about each episodes in podcasts, and listen to podcasts episodes. The application follows the clean architecture principles, using MVVM (Model-View-ViewModel) design pattern, Dagger-Hilt for dependency injection, and Retrofit for network requests.
## Features
- Display a list of Podcasts.
- View detailed information about episodes from podcasts.
- Play and listen to episodes
- Clean architecture with MVVM design pattern.
- Use of Jetpack Compose for UI.
## Technologies Used
- Kotlin
- Jetpack Compose
- Dagger-Hilt
- Retrofit
- Media3 Exoplayer
- Coil for image loading
- Coroutines and Flow
- JUnit and Mockito for testing
## Architecture
- **Presentation Layer**: Contains UI components and ViewModels.
- **Domain Layer**: Contains use cases and business logic.
- **Data Layer**: Contains repositories and data sources (remote).
## Getting Started
### Prerequisites
- Android Studio Bumblebee or higher
- Kotlin 1.5.21 or higher
- Gradle 7.0.2 or higher
### Installation
- Clone the repository
  
   ```sh
   git clone https://github.com/aghos4306/podcast-app.git
   cd podcast-app
- Open the project in Android Studio.
- Add your PODCAST_BASE_URL and IMAGE_BASE_URL in local.properties. The API does not require a key, but I used the base url to demonstrate how you can implement some level of security if you have private key that needs to be securely stored
- Build and run the project
  ```sh
  ./gradlew build

### Run Test
- Run test using the command on Android studio
  ```sh
  ./gradlew test

### Assumption
- If it was a production app, I will write viewmodel test, end to end test
- I will implement robust media control
- I will implement background service for media player

### Acknowledgement
- Podcast api https://the-podcasts.fly.dev/
