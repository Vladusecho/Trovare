package com.vladusecho.trovare.data.repository

import com.vladusecho.trovare.data.mapper.moviesDtoToMoviesMapper
import com.vladusecho.trovare.data.remote.ApiFactory
import com.vladusecho.trovare.domain.model.Movie
import com.vladusecho.trovare.domain.model.SearchResponse
import com.vladusecho.trovare.domain.repository.MovieRepository

object MovieRepositoryImpl : MovieRepository {

    val apiService = ApiFactory.apiService

    override suspend fun getMovieByName(query: String): SearchResponse {
        val response = apiService.getMovies(query)
        val movies = response.docs.moviesDtoToMoviesMapper()
        return SearchResponse(movies, response.total)
    }
}
