package com.sudzusama.pokemvi.ui.feature.berries

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.sudzusama.pokemvi.ui.feature.Navigations
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect

@Composable
fun BerriesScreen(navController: NavController) {
    val viewModel = hiltViewModel<BerriesViewModel>()
    BerriesContent(navController, viewModel.state.value, viewModel.effect, viewModel::setEvent)
}

@Composable
fun BerriesContent(
    navController: NavController,
    state: Berries.State,
    effectFlow: SharedFlow<Berries.Effect>,
    onEvent: (Berries.Event) -> Unit
) {
    LaunchedEffect(LISTEN_FOR_EFFECTS) {
        effectFlow.collect { effect ->
            when (effect) {
                is Berries.Effect.Navigation.ToBerryScreen -> navController.navigate(
                    Navigations.Route.BERRY.plus("/${effect.name}")
                )
                is Berries.Effect.Navigation.GoBack -> navController.popBackStack()
            }
        }
    }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = stringResource(R.string.berries_title)) },
            navigationIcon = {
                IconButton(onClick = { onEvent(Berries.Event.GoBack) }) {
                    Icon(Icons.Filled.ArrowBack, stringResource(R.string.navigation_go_back))
                }
            }
        )
    }) {
        if (state.isLoading) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        } else {
            if (state.berriesList.isNotEmpty()) {
                BerriesList(state, onEvent)
            } else {
                NothingToShowScreen()
            }
        }
    }
}

@Composable
fun BerriesList(state: Berries.State, onEvent: (Berries.Event) -> Unit) {
    LazyColumn{
        items(state.berriesList) { berry ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clickable { onEvent(Berries.Event.SelectBerry(berry.name)) },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = berry.name, modifier = Modifier)
            }
        }
    }
}


@Composable
fun NothingToShowScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.berries_nothing_to_show),
            textAlign = TextAlign.Center
        )
    }
}