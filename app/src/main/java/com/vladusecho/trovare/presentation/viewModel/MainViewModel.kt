package com.vladusecho.trovare.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladusecho.trovare.data.repository.MovieRepositoryImpl
import com.vladusecho.trovare.domain.model.Movie
import com.vladusecho.trovare.domain.usecase.GetMovieByNameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _state = MutableStateFlow<MainState>(MainState.Initial)
    val state = _state.asStateFlow()

    val repository = MovieRepositoryImpl
    val getMoviesByNameUseCase = GetMovieByNameUseCase(repository)

    fun processCommand(command: MainCommand) {
        when (command) {
            is MainCommand.InputQuery -> {
                viewModelScope.launch {
                    val movies = getMoviesByNameUseCase(command.query)
                    _state.value = MainState.Content(movies)
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
            val movies: List<Movie>
        ) : MainState
        object Error : MainState
        object Loading : MainState
    }
}