package com.sudzusama.pokemvi.ui.feature.berries

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sudzusama.pokemvi.data.PokeRepository
import com.sudzusama.pokemvi.data.response.BerriesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BerriesViewModel @Inject constructor(private val pokeRepository: PokeRepository) : ViewModel() {
    val test = MutableLiveData<BerriesResponse>()

    init {
        viewModelScope.launch { test.value = pokeRepository.getBerries(0, 50) }
    }
}