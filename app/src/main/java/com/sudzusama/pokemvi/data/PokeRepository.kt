package com.sudzusama.pokemvi.data

import com.sudzusama.pokemvi.data.network.PokeApi

class PokeRepository(private val api: PokeApi) {
    suspend fun getBerries(offset: Int?, count: Int) = api.getBerries(offset, count)
}