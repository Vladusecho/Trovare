package com.vladusecho.trovare.data.mapper

import com.vladusecho.trovare.data.remote.model.MovieDetailsDto
import com.vladusecho.trovare.domain.model.MovieDetails
import com.vladusecho.trovare.domain.model.MovieRating

fun MovieDetailsDto.movieDtoToMovieMapper(): MovieDetails {
    return MovieDetails(
        id = id,
        name = name,
        year = year,
        description = description,
        movieLength = movieLength,
        ageRating = ageRating,
        poster = poster?.url,
        genres = genres.map { it.name },
        type = type,
        seriesLength = seriesLength,
        rating = MovieRating(
            kp = if (rating?.kp == null) "-" else rating.kp.toString(),
            imdb = if (rating?.imdb == null) "-" else rating.imdb.toString()
        )
    )
}
