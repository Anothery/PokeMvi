package com.sudzusama.pokemvi.ui.feature.berries

import com.sudzusama.pokemvi.data.response.BerriesResponse

object Berries {
    data class State(
        val berriesList: List<BerriesResponse.BerryShortDetail> = listOf(),
        val isLoading: Boolean = true
    )

    sealed class Event {
        data class SelectBerry(val name: String) : Event()
        object GoBack : Event()
    }

    sealed class Effect {
        sealed class Navigation : Effect() {
            data class ToBerryScreen(val name: String) : Navigation()
            object GoBack : Navigation()
        }
    }
}