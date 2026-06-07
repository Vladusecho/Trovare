package com.vladusecho.trovare.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladusecho.trovare.domain.model.Movie
import com.vladusecho.trovare.domain.model.MovieDetails
import com.vladusecho.trovare.domain.usecase.GetMovieByIdUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel(
    assistedFactory = MovieDetailsScreenViewModel.Factory::class
)
class MovieDetailsScreenViewModel @AssistedInject constructor(
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    @Assisted("movieId") private val movieId: Int
) : ViewModel()
{
    private val _state = MutableStateFlow<MovieDetailsScreenState>(MovieDetailsScreenState.Initial)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = MovieDetailsScreenState.Loading
            val movieDetails = getMovieByIdUseCase(movieId)
            _state.value = MovieDetailsScreenState.Content(movieDetails)
        }
    }

    sealed interface MovieDetailsScreenState {
        object Initial : MovieDetailsScreenState
        object Loading : MovieDetailsScreenState
        object Error : MovieDetailsScreenState
        data class Content(val movieDetails: MovieDetails) : MovieDetailsScreenState
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("movieId") movieId: Int
        ): MovieDetailsScreenViewModel
    }
}