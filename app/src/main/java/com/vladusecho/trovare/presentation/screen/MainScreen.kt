package com.vladusecho.trovare.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vladusecho.trovare.presentation.viewModel.MainViewModel

@Composable
fun MainScreen(

) {

    val viewModel = viewModel<MainViewModel>()
    val state = viewModel.state.collectAsState()
    val currentState = state.value

    LaunchedEffect(Unit) {
        viewModel.processCommand(MainViewModel.MainCommand.InputQuery("брат"))
    }

    Scaffold() { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            when (currentState) {
                is MainViewModel.MainState.Content -> {
                    LazyColumn() {
                        items(
                            items = currentState.movies,
                            key = { it.id }
                        ) {
                            Text(
                                text = it.description,
                                color = Color.Black
                            )
                        }
                    }
                }

                is MainViewModel.MainState.Error -> {}
                is MainViewModel.MainState.Initial -> {}
                is MainViewModel.MainState.Loading -> {}
            }
        }
    }
}