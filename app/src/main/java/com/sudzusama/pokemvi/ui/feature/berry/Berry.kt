package com.sudzusama.pokemvi.ui.feature.berry

object Berry {
    data class State(val name: String = "")

    sealed class Event {
        object GoBack : Event()
    }

    sealed class Effect {
        sealed class Navigation : Effect() {
            object GoBack : Navigation()
        }
    }
}