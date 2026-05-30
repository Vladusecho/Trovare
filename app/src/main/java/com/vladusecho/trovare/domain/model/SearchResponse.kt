package com.vladusecho.trovare.domain.model

data class SearchResponse(
    val movies: List<Movie>,
    val total: Int
)