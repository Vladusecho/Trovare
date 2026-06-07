package com.vladusecho.trovare.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.vladusecho.trovare.presentation.nav.AppNavGraph
import com.vladusecho.trovare.presentation.nav.NavItem
import com.vladusecho.trovare.presentation.nav.NavScreen
import com.vladusecho.trovare.presentation.nav.NavigationState
import com.vladusecho.trovare.presentation.nav.rememberNavigationState
import com.vladusecho.trovare.presentation.ui.theme.TrovareTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navState = rememberNavigationState()
            val backStackEntry by navState.navHostController.currentBackStackEntryAsState()

            val rootScreens = listOf(
                NavScreen.Home::class,
                NavScreen.Favorite::class,
                NavScreen.Profile::class,
                NavScreen.Settings::class
            )

            TrovareTheme {
                Scaffold(
                    containerColor = Color.Transparent,
                    bottomBar = {
                        val currentDestination = backStackEntry?.destination
                        val shouldShowBottomBar =
                            rootScreens.any { currentDestination?.hasRoute(it) == true }
                        if (shouldShowBottomBar) {
                            BottomNavigationBar(navState = navState)
                        }
                    }
                ) { paddingValues ->
                    val padding = paddingValues
                    AppNavGraph(
                        navState = navState,
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navState: NavigationState
) {

    val backStackEntry by navState.navHostController.currentBackStackEntryAsState()

    val navItems = listOf(
        NavItem.Home,
        NavItem.Favorite,
        NavItem.Profile,
        NavItem.Settings
    )


    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 56.dp)
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(40.dp))
            .clip(RoundedCornerShape(40.dp))
            .background(Color(0xFFF5F5F5))

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            navItems.forEach { tab ->

                val isSelected =
                    backStackEntry?.destination?.hierarchy?.any {
                        it.hasRoute(tab.screen::class)
                    } ?: false

                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .clickable {
                                navState.navigateTo(tab.screen)
                            }
                            .background(if (isSelected) Color.White else Color.Transparent)
                    ) { }
                    Icon(
                        modifier = Modifier
                            .padding(14.dp),
                        painter = painterResource(tab.iconId),
                        contentDescription = null,
                        tint = if (isSelected) Color(0xff3544C4) else Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
fun BottomNavigationBarPreview() {
    TrovareTheme {
        BottomNavigationBar(
            navState = rememberNavigationState()
        )
    }
}