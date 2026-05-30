package com.vladusecho.trovare.data.repository

import com.vladusecho.trovare.data.mapper.moviesDtoToMoviesMapper
import com.vladusecho.trovare.data.remote.ApiFactory
import com.vladusecho.trovare.data.remote.ApiService
import com.vladusecho.trovare.domain.model.Movie
import com.vladusecho.trovare.domain.model.SearchResponse
import com.vladusecho.trovare.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService

) : MovieRepository {

    override suspend fun getMovieByName(query: String): SearchResponse {
        val response = apiService.getMovies(query)
        val movies = response.docs.moviesDtoToMoviesMapper()
        return SearchResponse(movies, response.total)
    }
}
