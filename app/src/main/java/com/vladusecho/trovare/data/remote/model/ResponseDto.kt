package com.vladusecho.trovare.data.remote.model

import com.google.gson.annotations.SerializedName

data class ResponseDto(
    @SerializedName("docs") val docs: List<MovieDto>
)
