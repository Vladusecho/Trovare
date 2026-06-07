package com.vladusecho.trovare.presentation.nav

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.vladusecho.trovare.presentation.screen.FavouriteScreen
import com.vladusecho.trovare.presentation.screen.HomeScreen
import com.vladusecho.trovare.presentation.screen.MovieDetailsScreen
import com.vladusecho.trovare.presentation.screen.ProfileScreen
import com.vladusecho.trovare.presentation.screen.SearchScreen
import com.vladusecho.trovare.presentation.screen.SettingsScreen

@Composable
fun AppNavGraph(
    navState: NavigationState,
) {
    NavHost(
        navController = navState.navHostController,
        startDestination = NavScreen.Home,
        enterTransition = {
            fadeIn(animationSpec = tween(durationMillis = 0))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(durationMillis = 0))
        }
    ) {
        composable<NavScreen.Home> {
            HomeScreen(
                onSearchClick = { navState.navigateTo(NavScreen.Search) },
                onMovieClick = { navState.navigateTo(NavScreen.MovieDetails(it)) }
            )
        }
        composable<NavScreen.Search> {
            SearchScreen(
                onBackClick = { navState.navHostController.navigateUp() }
            )
        }
        composable<NavScreen.Profile> {
            ProfileScreen(

            )
        }
        composable<NavScreen.Settings> {
            SettingsScreen(

            )
        }
        composable<NavScreen.Favorite> {
            FavouriteScreen(

            )
        }
        composable<NavScreen.MovieDetails> { backStackEntry ->
            val args = backStackEntry.toRoute<NavScreen.MovieDetails>()
            MovieDetailsScreen(
                movieId = args.movieId,
                onBackClick = { navState.navHostController.navigateUp() }
            )
        }
    }
}