package com.vladusecho.trovare.presentation.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vladusecho.trovare.presentation.screen.HomeScreen
import com.vladusecho.trovare.presentation.screen.SearchScreen

@Composable
fun AppNavGraph(
    navState: NavigationState,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navState.navHostController,
        startDestination = NavScreen.Home,
    ) {
        composable<NavScreen.Home> {
            HomeScreen(
                paddingValues = innerPadding,
                onSearchClick = { navState.navigateTo(NavScreen.Search) }
            )
        }
        composable<NavScreen.Search> {
            SearchScreen(
                paddingValues = innerPadding,
                onBackClick = { navState.navHostController.navigateUp() }
            )
        }
    }

}