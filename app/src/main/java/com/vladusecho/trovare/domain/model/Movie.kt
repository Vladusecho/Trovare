package com.vladusecho.trovare.domain.model

data class Movie(
    val id: Int,
    val name: String,
    val year: Int,
    val description: String,
    val movieLength: Int,
    val ageRating: Int,
)
