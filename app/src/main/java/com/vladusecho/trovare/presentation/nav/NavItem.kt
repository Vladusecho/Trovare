package com.vladusecho.trovare.presentation.nav

import com.vladusecho.trovare.R

sealed class NavItem(
    val iconId: Int,
    val screen: Any
) {

    object Home : NavItem(
        R.drawable.ic_nav_home, NavScreen.Home
    )

    object Favorite : NavItem(
        R.drawable.ic_nav_favourite, NavScreen.Favorite
    )

    object Profile : NavItem(
        R.drawable.ic_nav_profile, NavScreen.Profile
    )

    object Settings : NavItem(
        R.drawable.ic_nav_settings, NavScreen.Settings
    )
}
