package com.sudzusama.pokemvi.data.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BerriesResponse(
    @Json(name = "count")
    val count: Int? = 0,
    @Json(name = "next")
    val next: String? = "",
    @Json(name = "previous")
    val previous: String? = "",
    @Json(name = "results")
    val results: List<BerryShortDetail>? = listOf()
) {
    @JsonClass(generateAdapter = true)
    data class BerryShortDetail(
        @Json(name = "name")
        val name: String = "",
        @Json(name = "url")
        val url: String = ""
    )
}