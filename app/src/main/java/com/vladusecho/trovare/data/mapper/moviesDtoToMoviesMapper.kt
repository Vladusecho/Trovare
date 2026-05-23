package com.vladusecho.trovare.data.mapper

import com.vladusecho.trovare.data.remote.model.MovieDto
import com.vladusecho.trovare.domain.model.Movie

fun List<MovieDto>.moviesDtoToMoviesMapper(): List<Movie> {
    return map { movieDto ->
        movieDto.movieDtoToMovieMapper()
    }
}