package com.sudzusama.pokemvi.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

const val LISTEN_FOR_EFFECTS = "LISTEN_FOR_EFFECTS"

abstract class BaseViewModel<State, Event, Effect>(initialState: State) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> get() = _state

    private val _effect = MutableSharedFlow<Effect>()
    val effect = _effect.asSharedFlow()

    private val _event = MutableSharedFlow<Event>()

    init {
        viewModelScope.launch { _event.collect(::handleEvents) }
    }

    protected suspend fun setState(reducer: State.() -> State) {
        _state.emit(_state.value.reducer())
    }


    fun setEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

    abstract fun handleEvents(event: Event)

    protected fun setEffect(builder: () -> Effect) {
        viewModelScope.launch { _effect.emit(builder()) }
    }
}