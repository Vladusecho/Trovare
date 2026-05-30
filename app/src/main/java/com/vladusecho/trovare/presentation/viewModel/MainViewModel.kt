package com.vladusecho.trovare.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladusecho.trovare.data.repository.MovieRepositoryImpl
import com.vladusecho.trovare.domain.model.Movie
import com.vladusecho.trovare.domain.usecase.GetMovieByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val getMoviesByNameUseCase: GetMovieByNameUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<MainState>(MainState.Initial)
    val state = _state.asStateFlow()

    fun processCommand(command: MainCommand) {
        when (command) {
            is MainCommand.InputQuery -> {
                viewModelScope.launch {
                    _state.value = MainState.Loading
                    val searchResponse = getMoviesByNameUseCase(command.query)
                    _state.value = MainState.Content(
                        movies = searchResponse.movies,
                        total = searchResponse.total
                    )
                }
            }
        }
    }

    sealed interface MainCommand {
        data class InputQuery(
            val query: String
        ) : MainCommand
    }

    sealed interface MainState {
        object Initial : MainState
        data class Content(
            val movies: List<Movie>,
            val total: Int
        ) : MainState
        object Error : MainState
        object Loading : MainState
    }
}