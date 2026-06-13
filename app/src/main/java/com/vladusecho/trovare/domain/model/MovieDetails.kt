package com.vladusecho.trovare.domain.model

import com.google.gson.annotations.SerializedName

data class MovieDetails(
    val id: Int,
    val name: String,
    val year: Int,
    val description: String,
    val movieLength: String?,
    val seriesLength: String?,
    val rating: MovieRating?,
    val ageRating: Int,
    val poster: String?,
    val type: Type,
    val genres: List<String>
)

enum class Type(title: String) {
    @SerializedName("movie")
    MOVIE("movie"),
    @SerializedName("tv-series")
    TV_SERIES("tv_series")
}