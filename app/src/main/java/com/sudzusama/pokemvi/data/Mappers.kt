package com.sudzusama.pokemvi.data

import com.sudzusama.pokemvi.data.response.BerriesResponse
import com.sudzusama.pokemvi.model.BerryShort

fun BerriesResponse.BerryShortDetail.toDomain() = BerryShort(this.name, this.url)

