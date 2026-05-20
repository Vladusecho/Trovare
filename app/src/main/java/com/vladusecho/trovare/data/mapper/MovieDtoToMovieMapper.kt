package com.vladusecho.trovare.data.mapper

import com.vladusecho.trovare.data.remote.model.MovieDto
import com.vladusecho.trovare.domain.model.Movie

fun MovieDto.MovieDtoToMovieMapper(): Movie {
    return Movie(
        id = id,
        name = name,
        year = year,
        description = description,
        movieLength = movieLength,
        ageRating = ageRating,
    )
}