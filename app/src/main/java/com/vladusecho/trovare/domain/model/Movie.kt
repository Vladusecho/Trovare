package com.vladusecho.trovare.domain.model

data class Movie(
    val id: Int,
    val name: String,
    val year: Int,
    val description: String,
    val movieLength: String,
    val ageRating: Int,
    val poster: String?,
    val genres: List<String>,
    val rating: MovieRating
)
