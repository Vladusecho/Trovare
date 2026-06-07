package com.vladusecho.trovare.domain.usecase

import com.vladusecho.trovare.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieByIdUseCase @Inject constructor(
    private val movieRepository: MovieRepository
){
    suspend operator fun invoke(id: Int) = movieRepository.getMovieById(id)
}