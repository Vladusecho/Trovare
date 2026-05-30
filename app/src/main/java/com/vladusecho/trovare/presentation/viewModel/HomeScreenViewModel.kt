package com.vladusecho.trovare.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladusecho.trovare.domain.model.Movie
import com.vladusecho.trovare.domain.usecase.GetMovieByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    val getMovieByNameUseCase: GetMovieByNameUseCase
): ViewModel() {

    private val _state = MutableStateFlow<HomeScreenState>(HomeScreenState.Initial)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = HomeScreenState.Loading
            val searchResponse = getMovieByNameUseCase("")
            _state.value = HomeScreenState.Content(
                movies = searchResponse.movies
            )
        }
    }

    sealed interface HomeScreenState {
        object Initial : HomeScreenState
        object Error : HomeScreenState
        object Loading : HomeScreenState
        data class Content(
            val movies: List<Movie>
        ) : HomeScreenState
    }
}