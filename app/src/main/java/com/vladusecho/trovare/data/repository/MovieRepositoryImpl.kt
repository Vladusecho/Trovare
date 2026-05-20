package com.vladusecho.trovare.data.repository

import com.vladusecho.trovare.data.mapper.MoviesDtoToMoviesMapper
import com.vladusecho.trovare.data.remote.ApiFactory
import com.vladusecho.trovare.domain.model.Movie
import com.vladusecho.trovare.domain.repository.MovieRepository

object MovieRepositoryImpl : MovieRepository {

    val apiService = ApiFactory.apiService

    override suspend fun getMovieByName(query: String): List<Movie> {
        return apiService.getMovies(query).docs.MoviesDtoToMoviesMapper()
    }
}