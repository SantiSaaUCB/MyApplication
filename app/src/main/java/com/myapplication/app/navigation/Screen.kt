package com.myapplication.app.navigation

sealed class Screen(val route: String, val label: String) {
    object Github : Screen("github", "Github")
    object Movie : Screen("movie", "Movie")
    object Login : Screen("login", "Login")
    object Profile : Screen("profile", "Profile")
    object Dollar : Screen("dollar", "Dollar")
}

val BottomScreens = listOf(
    Screen.Github,
    Screen.Movie,
    Screen.Login,
    Screen.Profile,
    Screen.Dollar
)
