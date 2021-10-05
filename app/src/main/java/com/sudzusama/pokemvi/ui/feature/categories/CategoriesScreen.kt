package com.sudzusama.pokemvi.ui.feature.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.sudzusama.pokemvi.R
import com.sudzusama.pokemvi.ui.base.LISTEN_FOR_EFFECTS
import com.sudzusama.pokemvi.ui.feature.Navigations
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect


@Composable
fun CategoriesScreen(navController: NavController) {
    val viewModel = hiltViewModel<CategoriesViewModel>()
    CategoriesContent(navController, viewModel.state.value, viewModel.effect, viewModel::setEvent)
}

@Composable
fun CategoriesContent(
    navController: NavController,
    state: Categories.State,
    effectFlow: SharedFlow<Categories.Effect>,
    onEvent: (Categories.Event) -> Unit
) {
    LaunchedEffect(LISTEN_FOR_EFFECTS) {
        effectFlow.collect { effect ->
            when (effect) {
                is Categories.Effect.Navigation.ToBerries -> navController.navigate(Navigations.Route.BERRIES)
                //    is Categories.Effect.Navigation.ToLocations -> navController.navigate(Navigations.Route.LOCATIONS)
                //    is Categories.Effect.Navigation.ToPokemons -> navController.navigate(Navigations.Route.POKEMONS)
            }
        }
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text(stringResource(R.string.app_name)) }, elevation = 2.dp)
    }) {
        if (state.isLoading) {
            Loading()
        } else {
            if (state.categoriesList.isEmpty()) {
                NothingToShow()
            } else {
                CategoriesList(state, onEvent)
            }
        }
    }
}

@Composable
fun CategoriesList(
    state: Categories.State,
    onEvent: (Categories.Event) -> Unit
) {

    LazyColumn {
        items(state.categoriesList) { category ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clickable { onEvent(Categories.Event.SelectCategory(category.name)) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberImagePainter(category.iconUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .padding(8.dp)
                )
                Text(text = category.name, modifier = Modifier.padding(8.dp))
            }
        }

    }
}

@Composable
fun Loading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) { CircularProgressIndicator() }
}

@Composable
fun NothingToShow() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) { CircularProgressIndicator() }
}

