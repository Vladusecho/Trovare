package com.vladusecho.trovare.domain.repository

import com.vladusecho.trovare.domain.model.Movie
import com.vladusecho.trovare.domain.model.MovieDetails
import com.vladusecho.trovare.domain.model.SearchResponse

interface MovieRepository {

    suspend fun getMovieByName(query: String): SearchResponse

    suspend fun getMovieById(id: Int): MovieDetails
}