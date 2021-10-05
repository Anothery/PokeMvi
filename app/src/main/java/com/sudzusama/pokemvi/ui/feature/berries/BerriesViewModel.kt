package com.sudzusama.pokemvi.ui.feature.berries

import androidx.lifecycle.viewModelScope
import com.sudzusama.pokemvi.data.PokeRepository
import com.sudzusama.pokemvi.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BerriesViewModel @Inject constructor(
    private val pokeRepository: PokeRepository
) : BaseViewModel<Berries.State, Berries.Event, Berries.Effect>(Berries.State()) {

    init {
        viewModelScope.launch {
            val berries = pokeRepository.getBerries(0, 50)
            setState { copy(berriesList = berries.results ?: listOf(), isLoading = false) }
        }
    }

    override fun handleEvents(event: Berries.Event) = when (event) {
        is Berries.Event.SelectBerry -> setEffect { Berries.Effect.Navigation.ToBerryScreen(event.name) }
        is Berries.Event.GoBack -> setEffect { Berries.Effect.Navigation.GoBack }
    }
}


