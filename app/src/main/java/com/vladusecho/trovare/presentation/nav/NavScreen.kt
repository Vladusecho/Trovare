package com.vladusecho.trovare.presentation.nav

import kotlinx.serialization.Serializable

sealed class NavScreen {

    @Serializable
    object Home : NavScreen()

    @Serializable
    object Search : NavScreen()

    @Serializable
    object Profile : NavScreen()


}