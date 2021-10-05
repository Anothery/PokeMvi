package com.sudzusama.pokemvi.ui.feature

import com.sudzusama.pokemvi.ui.feature.Navigations.Arg.BERRY_NAME

class Navigations {
    object Arg {
        const val BERRY_NAME = "BERRY_NAME"
    }

    object Route {
        const val CATEGORIES = "CATEGORIES"
        const val BERRIES = "BERRIES"
        const val BERRY = "BERRY"
        const val BERRY_DETAILS = "${BERRY}/{${BERRY_NAME}}"
        const val LOCATIONS = "LOCATIONS"
        const val POKEMONS = "POKEMONS"
    }
}