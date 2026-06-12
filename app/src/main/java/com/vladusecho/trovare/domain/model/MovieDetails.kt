package com.vladusecho.trovare.domain.model

import com.vladusecho.trovare.data.remote.model.PosterDto

data class MovieDetails(
    val id: Int,
    val name: String,
    val year: Int,
    val description: String,
    val movieLength: String,
    val ageRating: Int,
    val poster: String?,
    val genres: List<String>
)