package com.vladusecho.trovare.domain.usecase

import com.vladusecho.trovare.domain.model.Movie
import com.vladusecho.trovare.domain.model.SearchResponse
import com.vladusecho.trovare.domain.repository.MovieRepository

class GetMovieByNameUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(name: String): SearchResponse {
        return repository.getMovieByName(name)
    }
}