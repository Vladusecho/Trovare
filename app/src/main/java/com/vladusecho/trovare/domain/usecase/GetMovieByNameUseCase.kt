package com.vladusecho.trovare.domain.usecase

import com.vladusecho.trovare.domain.model.Movie
import com.vladusecho.trovare.domain.repository.MovieRepository

class GetMovieByNameUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(name: String): List<Movie> {
        return repository.getMovieByName(name)
    }
}