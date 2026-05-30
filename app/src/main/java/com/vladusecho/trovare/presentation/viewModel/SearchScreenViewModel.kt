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
class SearchScreenViewModel @Inject constructor(
    val getMoviesByNameUseCase: GetMovieByNameUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<SearchScreenState>(SearchScreenState.Initial)
    val state = _state.asStateFlow()

    fun processCommand(command: SearchScreenCommand) {
        when (command) {
            is SearchScreenCommand.InputQuery -> {
                viewModelScope.launch {
                    _state.value = SearchScreenState.Loading
                    val searchResponse = getMoviesByNameUseCase(command.query)
                    _state.value = SearchScreenState.Content(
                        movies = searchResponse.movies,
                        total = searchResponse.total
                    )
                }
            }
        }
    }

    sealed interface SearchScreenCommand {
        data class InputQuery(
            val query: String
        ) : SearchScreenCommand
    }

    sealed interface SearchScreenState {
        object Initial : SearchScreenState
        data class Content(
            val movies: List<Movie>,
            val total: Int
        ) : SearchScreenState
        object Error : SearchScreenState
        object Loading : SearchScreenState
    }
}