package com.sopt.dive.presentation.ui.main.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import com.sopt.dive.presentation.ui.home.navigation.homeNavGraph
import com.sopt.dive.presentation.ui.login.navigation.loginNavGraph
import com.sopt.dive.presentation.ui.profile.navigation.profileNavGraph
import com.sopt.dive.presentation.ui.search.navigation.searchNavGraph
import com.sopt.dive.presentation.ui.signup.navigation.signupNavGraph
import com.sopt.dive.presentation.ui.splash.navigation.splashNavGraph

@Composable
fun MainNavHost(
    padding: PaddingValues,
    navigator: MainNavigator,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination
        ) {
            splashNavGraph(
                paddingValues = padding,
                onNavigateToLogin = navigator::navigateToLogin,
                onNavigateToHome = navigator::navigateToHome
            )

            loginNavGraph(
                paddingValues = padding,
                onNavigateToSignUp = navigator::navigateToSignup,
                onNavigateToHome = navigator::navigateToHome
            )

            signupNavGraph(
                paddingValues = padding,
                navController = navigator.navController
            )

            homeNavGraph(
                paddingValues = padding,
            )

            searchNavGraph(
                paddingValues = padding,
            )

            profileNavGraph(
                paddingValues = padding
            )
        }
    }
}