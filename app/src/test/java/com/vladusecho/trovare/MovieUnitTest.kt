package com.vladusecho.trovare

import com.vladusecho.trovare.data.remote.ApiService
import com.vladusecho.trovare.data.remote.model.GenreDto
import com.vladusecho.trovare.data.remote.model.MovieDto
import com.vladusecho.trovare.data.remote.model.PosterDto
import com.vladusecho.trovare.data.remote.model.RatingDto
import com.vladusecho.trovare.data.remote.model.ResponseDto
import com.vladusecho.trovare.data.repository.MovieRepositoryImpl
import com.vladusecho.trovare.domain.model.Movie
import com.vladusecho.trovare.domain.model.MovieRating
import com.vladusecho.trovare.domain.model.SearchResponse
import com.vladusecho.trovare.domain.usecase.GetMovieByNameUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import java.io.IOException


class MovieUnitTest {
    @Test
    fun `movies returns when query is valid`() {
        val mockRepository = mockk<MovieRepositoryImpl>()

        coEvery { mockRepository.getMovieByName("spider man") } returns SearchResponse(
            listOf(
                Movie(
                    id = 1,
                    name = "Spider-Man: No Way Home",
                    year = 2021,
                    description = "",
                    movieLength = "180",
                    ageRating = 16,
                    poster = "",
                    genres = listOf("Action", "Adventure"),
                    rating = MovieRating(
                        kp = "8.5",
                        imdb = "8.5"
                    )
                )
            ),
            total = 1
        )

        val useCase = GetMovieByNameUseCase(mockRepository)
        val result = runBlocking {
            useCase("spider man")
        }

        assertEquals(1, result.movies.size)
        assertEquals("Spider-Man: No Way Home", result.movies[0].name)

        coVerify(exactly = 1) { mockRepository.getMovieByName("spider man") }
    }
}
