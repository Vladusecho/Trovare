package com.vladusecho.trovare.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.vladusecho.trovare.presentation.nav.AppNavGraph
import com.vladusecho.trovare.presentation.nav.rememberNavigationState
import com.vladusecho.trovare.presentation.ui.theme.TrovareTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            TrovareTheme {
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar()
                    }
                ) {
                    AppNavGraph(
                        navState = rememberNavigationState(),
                        innerPadding = it
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {

}