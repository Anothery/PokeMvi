package com.sudzusama.pokemvi.ui.feature.berries

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sudzusama.pokemvi.data.response.BerriesResponse

@Composable
fun BerriesScreen(navController: NavController) {
    val viewModel = hiltViewModel<BerriesViewModel>()
    val berriesResponse by viewModel.test.observeAsState()
    BerriesContent(navController, berriesResponse = berriesResponse)
}

@Composable
fun BerriesContent(navController: NavController, berriesResponse: BerriesResponse?) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Berries") }, navigationIcon = {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.graphicsLayer { alpha = 1f }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go back")
            }
        }, elevation = 2.dp)
    }) {
        berriesResponse?.results?.let { berries ->
            if (berries.isNotEmpty()) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(berries) { berry ->
                        Text(
                            text = berry.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        )
                    }
                }
            } else {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "Nothing to show",
                        textAlign = TextAlign.Center
                    )
                }
            }

        } ?: Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }
}