package com.vladusecho.trovare.presentation.nav

import com.vladusecho.trovare.domain.model.Movie
import kotlinx.serialization.Serializable

sealed class NavScreen {

    @Serializable
    object Home : NavScreen()

    @Serializable
    object Search : NavScreen()

    @Serializable
    object Profile : NavScreen()

    @Serializable
    object Settings : NavScreen()
    @Serializable
    object Favorite : NavScreen()

    @Serializable
    data class MovieDetails(val movieId: Int) : NavScreen()
}