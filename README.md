# testPunchInAndOut



## Functionality Overview:
Upon launching, the app checks the device type (phone or tablet) based on screen size to optimize the layout. Users are prompted to input their email in the PhoneViewScreen. Depending on their work status that is fetched from server (isAtWork true or false), the app allows them to punch in or out respectively. 
The app makes use of Retrofit to handle network communication with the server, retrieving punch clock information associated with the user's email. It updates and displays this information dynamically in the UI.
The architecture follows an MVVM pattern, enhancing maintainability and scalability by separating concerns between UI, data, and business logic.

## 1. MainActivity.kt
Determines device type (tablet or phone) based on screen size.
Sets up navigation using Jetpack Compose.

## 3. PhoneViewScreen.kt
Composable function for the phone's punch screen.
Allows users to input their email and perform punch in/out actions.
Makes network requests using Retrofit based on user actions.

## 5. RetrofitClient.kt
Creates a Retrofit instance to handle API requests.
Defines the base URL and creates a PunchClockService using Retrofit.

## 7. PhoneNavGraph.kt
Sets up the navigation graph for the phone layout using Jetpack Navigation Compose.
Defines destinations for view and punch screens with arguments.

## 9. SharedViewModel.kt
Manages shared data across the app using mutableStateOf.
Stores and updates worker info response data.

## 11. PunchClockService.kt
Interface defining API endpoints for punch in/out actions.

## 13. PunchClockResponse.kt
Data class defining the punch clock response structure.

## 15. PunchInOutResponse.kt
Data class representing punch in/out time and email.

## 17. PhonePunchScreen.kt
Composable function displaying punch actions based on the user's work status.
Handles punch in/out functionality using Retrofit based on user actions.

This setup allows users to input their email, track punch clock information, and perform punch in/out actions within the app. It utilizes Jetpack Compose for UI, Retrofit for network requests, and follows an MVVM architecture pattern.
