package com.sudzusama.pokemvi.data.network

import com.sudzusama.pokemvi.data.response.BerriesResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface PokeApi {
    @GET("berry")
    suspend fun getBerries(@Query("offset") offset: Int?, @Query("limit") count: Int): BerriesResponse

    companion object {
        const val API_URL = "https://pokeapi.co/api/v2/"
    }
}