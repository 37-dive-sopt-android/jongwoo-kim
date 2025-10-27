package com.sopt.dive.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Splash : Route

    @Serializable
    data object Login : Route

    @Serializable
    data object Signup: Route
}

sealed interface MainRoute : Route {
    @Serializable
    data object Home : MainRoute
}