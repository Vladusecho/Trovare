package com.vladusecho.trovare.domain.repository

import com.vladusecho.trovare.domain.model.Movie

interface MovieRepository {

    suspend fun getMovieByName(query: String): List<Movie>
}