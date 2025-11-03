package com.sopt.dive.presentation.ui.profile.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.core.navigation.MainRoute
import com.sopt.dive.presentation.ui.profile.ProfileRoute


fun NavController.navigateToProfile(navOptions: NavOptions) {
    navigate(MainRoute.Profile, navOptions)
}

fun NavGraphBuilder.profileNavGraph(
    paddingValues: PaddingValues
) {
    composable<MainRoute.Profile> { backStackEntry ->
        ProfileRoute(
            paddingValues = paddingValues
        )
    }
}