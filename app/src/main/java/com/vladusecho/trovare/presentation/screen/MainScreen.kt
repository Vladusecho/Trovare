package com.vladusecho.trovare.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vladusecho.trovare.R
import com.vladusecho.trovare.presentation.element.MovieCard
import com.vladusecho.trovare.presentation.ui.theme.TrovareTheme
import com.vladusecho.trovare.presentation.ui.theme.TrovareTypography
import com.vladusecho.trovare.presentation.viewModel.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(

) {

    val viewModel = viewModel<MainViewModel>()
    val state = viewModel.state.collectAsState()
    val currentState = state.value

    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Scaffold() { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xffF5F6F7))
        ) {
            Column() {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    MovieSearchBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        onBackClick = {

                        },
                        onSearchClick = {
                            viewModel.processCommand(MainViewModel.MainCommand.InputQuery(it))
                            scope.launch {
                                listState.scrollToItem(0)
                            }
                        }
                    )
                }
                when (currentState) {
                    is MainViewModel.MainState.Content -> {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                                .background(Color.White)
                        ) {
                            LazyColumn(
                                state = listState,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                item {
                                    Text(
                                        text = "Найдено ${currentState.total} фильмов",
                                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                                        fontFamily = TrovareTypography,
                                        fontWeight = FontWeight.Normal,
                                        color = Color.Black
                                    )
                                }
                                items(
                                    items = currentState.movies,
                                    key = { it.id }
                                ) {
                                    MovieCard(
                                        movie = it,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 16.dp)
                                    ) { }
                                    HorizontalDivider(
                                        modifier = Modifier.padding(horizontal = 32.dp).padding(top = 8.dp),
                                        color = Color.Gray,
                                        thickness = 0.5.dp
                                    )
                                }
                            }
                        }
                    }

                    is MainViewModel.MainState.Error -> {}
                    is MainViewModel.MainState.Initial -> {}
                    is MainViewModel.MainState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MovieSearchBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onSearchClick: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    val searchQuery = remember { mutableStateOf("") }

    OutlinedTextField(
        value = searchQuery.value,
        onValueChange = { searchQuery.value = it },
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        placeholder = {
            Text(
                text = "Поиск",
                color = Color.Gray,
                fontFamily = TrovareTypography,
                fontWeight = FontWeight.Normal
            )
        },
        trailingIcon = {
            IconButton({ onSearchClick(searchQuery.value) }) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_search),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        },
        leadingIcon = {
            IconButton(onBackClick) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_back_arrow),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            unfocusedTextColor = Color.Black,
            cursorColor = Color.Black,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        ),
        singleLine = true,
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchClick(searchQuery.value)
                focusManager.clearFocus()
            }
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        )
    )
}

@Composable
@Preview(showBackground = true)
fun MovieSearchBarPreview() {
    TrovareTheme() {
        MovieSearchBar(
            onBackClick = { },
            onSearchClick = { }
        )
    }
}