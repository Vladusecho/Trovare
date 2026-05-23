package com.vladusecho.trovare.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("year") val year: Int,
    @SerializedName("description") val description: String,
    @SerializedName("movieLength") val movieLength: Int,
    @SerializedName("ageRating") val ageRating: Int,
    @SerializedName("poster") val poster: PosterDto?,
    @SerializedName("genres") val genres: List<GenreDto>,
)
