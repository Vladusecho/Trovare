package com.vladusecho.trovare.data.mapper

import com.vladusecho.trovare.data.remote.model.MovieDetailsDto
import com.vladusecho.trovare.domain.model.Movie
import com.vladusecho.trovare.domain.model.MovieDetails

fun MovieDetailsDto.movieDtoToMovieMapper(): MovieDetails{
    return MovieDetails(
        id = id,
        name = name,
        year = year,
        description = description,
        movieLength = movieLength,
        ageRating = ageRating,
        poster = poster?.url
    )
}
