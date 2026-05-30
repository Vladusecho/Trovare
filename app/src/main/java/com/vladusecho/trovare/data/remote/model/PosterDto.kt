package com.vladusecho.trovare.data.remote.model

import com.google.gson.annotations.SerializedName

data class PosterDto(
    @SerializedName("url") val url: String,
)