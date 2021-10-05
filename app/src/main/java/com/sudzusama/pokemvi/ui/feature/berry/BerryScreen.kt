package com.sudzusama.pokemvi.ui.feature.berry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sudzusama.pokemvi.R
import com.sudzusama.pokemvi.ui.base.LISTEN_FOR_EFFECTS
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect

@Composable
fun BerryScreen(navController: NavController, berryName: String) {
    val viewModel = hiltViewModel<BerryViewModel>()
    BerryContent(
        navController,
        berryName,
        viewModel.state.value,
        viewModel.effect,
        viewModel::setEvent,
    )
}

@Composable
fun BerryContent(
    navController: NavController,
    berryName: String,
    state: Berry.State,
    effectFlow: SharedFlow<Berry.Effect>,
    onEvent: (Berry.Event) -> Unit
) {
    LaunchedEffect(LISTEN_FOR_EFFECTS) {
        effectFlow.collect { effect ->
            when (effect) {
                is Berry.Effect.Navigation.GoBack -> navController.popBackStack()
            }
        }
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = berryName) }, navigationIcon = {
            IconButton(onClick = { onEvent(Berry.Event.GoBack) }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    stringResource(R.string.navigation_go_back)
                )
            }
        }, elevation = 2.dp)
    }) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Nothing to show\n...\nDeveloping in progress",
                textAlign = TextAlign.Center
            )
        }
    }
}