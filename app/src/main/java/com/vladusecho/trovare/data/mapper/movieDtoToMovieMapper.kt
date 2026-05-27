package com.vladusecho.trovare.data.mapper

import com.vladusecho.trovare.data.remote.model.MovieDto
import com.vladusecho.trovare.domain.model.Movie
import com.vladusecho.trovare.domain.model.MovieRating

fun MovieDto.movieDtoToMovieMapper(): Movie {
    return Movie(
        id = id,
        name = name,
        year = year,
        description = description,
        movieLength = if (movieLength == 0) "-" else movieLength.toString(),
        ageRating = ageRating,
        poster = poster?.url,
        genres = genres.map { it.name },
        rating = MovieRating(
            kp = if (rating?.kp == null) "-" else rating.kp.toString(),
            imdb = if (rating?.imdb == null) "-" else rating.imdb.toString()
        )
    )
}
