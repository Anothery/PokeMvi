package com.sudzusama.pokemvi.ui.feature.categories

import androidx.lifecycle.viewModelScope
import com.sudzusama.pokemvi.model.Category
import com.sudzusama.pokemvi.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor() :
    BaseViewModel<Categories.State, Categories.Event, Categories.Effect>(Categories.State()) {

    init {
        val categoriesList = listOf(
            Category("Berries", "https://i.imgur.com/XOtbhO2.png"),
            Category("Locations", "https://i.imgur.com/XIe3lEB.png"),
            Category("Pokemons", "https://i.imgur.com/DAtrY4f.png")
        )

        viewModelScope.launch {
            setState { copy(categoriesList = categoriesList, isLoading = false) }
        }
    }

    override fun handleEvents(event: Categories.Event) {
        when (event) {
            is Categories.Event.SelectCategory -> {
                when (event.categoryName) {
                    "Berries" -> setEffect { Categories.Effect.Navigation.ToBerries }
                    "Locations" -> setEffect { Categories.Effect.Navigation.ToLocations }
                    "Pokemons" -> setEffect { Categories.Effect.Navigation.ToPokemons }
                }
            }
        }
    }

}