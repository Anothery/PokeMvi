package com.sudzusama.pokemvi.ui.feature.berry

import com.sudzusama.pokemvi.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BerryViewModel @Inject constructor() :
    BaseViewModel<Berry.State, Berry.Event, Berry.Effect>(Berry.State()) {

    override fun handleEvents(event: Berry.Event) {
        when (event) {
            is Berry.Event.GoBack -> setEffect { Berry.Effect.Navigation.GoBack }
        }
    }
}