package com.sudzusama.pokemvi.ui.feature.categories

import com.sudzusama.pokemvi.model.Category

object Categories {
    data class State(
        val categoriesList: List<Category> = listOf(),
        val isLoading: Boolean = true
    )

    sealed class Event {
        data class SelectCategory(val categoryName: String) : Event()
    }

    sealed class Effect {
        sealed class Navigation : Effect() {
            object ToBerries : Navigation()
            object ToLocations : Navigation()
            object ToPokemons : Navigation()
        }
    }
}