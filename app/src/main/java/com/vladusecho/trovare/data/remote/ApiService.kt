package com.vladusecho.trovare.data.remote

import com.vladusecho.trovare.data.remote.model.ResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1.4/movie/search?page=1&limit=10")
    suspend fun getMovies(
        @Query("query") query: String
    ): ResponseDto
}