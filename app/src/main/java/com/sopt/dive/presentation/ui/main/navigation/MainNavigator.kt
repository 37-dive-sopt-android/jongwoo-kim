package com.sopt.dive.presentation.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.sopt.dive.core.navigation.Route
import com.sopt.dive.presentation.ui.home.navigation.navigateToHome
import com.sopt.dive.presentation.ui.login.navigation.navigateToLogin
import com.sopt.dive.presentation.ui.main.MainTab
import com.sopt.dive.presentation.ui.profile.navigation.navigateToProfile
import com.sopt.dive.presentation.ui.search.navigation.navigateToSearch
import com.sopt.dive.presentation.ui.signup.navigation.navigateToSignup

class MainNavigator(
    val navController: NavHostController
) {
    private val currentDestination: NavDestination?
    @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val startDestination = Route.Splash

    val currentTab: MainTab?
        @Composable get() = MainTab.entries.find { tab ->
            when (tab.route) {
                else -> currentDestination?.route == tab.route::class.qualifiedName
            }
        }

    fun navigateToLogin() {
        navController.navigateToLogin(
            navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        )
    }

    fun navigateToSignup() {
        navController.navigateToSignup()
    }

    fun navigateToHome() {
        navController.navigateToHome(
            navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        )
    }

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(MainTab.HOME.route) {
                inclusive = false
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.HOME -> navController.navigateToHome(navOptions)
            MainTab.SEARCH -> navController.navigateToSearch(navOptions)
            MainTab.PROFILE -> navController.navigateToProfile(navOptions)
        }
    }

    @Composable
    fun shouldShowBottomBar() = MainTab.contains {
        currentDestination?.route == it::class.qualifiedName
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController()
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}