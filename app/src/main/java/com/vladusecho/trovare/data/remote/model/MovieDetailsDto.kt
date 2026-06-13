package com.vladusecho.trovare.data.remote.model

import com.google.gson.annotations.SerializedName
import com.vladusecho.trovare.domain.model.Type


data class MovieDetailsDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("year") val year: Int,
    @SerializedName("description") val description: String,
    @SerializedName("movieLength") val movieLength: String?,
    @SerializedName("seriesLength") val seriesLength: String?,
    @SerializedName("rating") val rating: RatingDto?,
    @SerializedName("ageRating") val ageRating: Int,
    @SerializedName("poster") val poster: PosterDto?,
    @SerializedName("type") val type: Type,
    @SerializedName("genres") val genres: List<GenreDto>
)