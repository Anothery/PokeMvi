package com.sudzusama.pokemvi.ui.feature.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.sudzusama.pokemvi.ui.feature.Navigations

@ExperimentalCoilApi
@Composable
fun CategoriesScreen(navController: NavController) {
    val categories =
        listOf(
            Category(
                "Berries",
                "https://static.wikia.nocookie.net/pokemon/images/3/34/Curry_Yache_Berry_Sprite.png/revision/latest?cb=20210212104713"
            ),
            Category(
                "Locations",
                "https://icons-for-free.com/iconfiles/png/512/go+location+play+pokemon+icon-1320186976262381767.png"
            ),
            Category(
                "Pokemons",
                "https://img.icons8.com/color/452/pokemon.png"
            )
        )
    Scaffold(topBar = { TopAppBar(title = { Text(text = "PokeMvi") }, elevation = 2.dp) }) {
        LazyColumn {
            items(categories) { Category(navController, it) }
        }
    }
}

@Composable
fun Category(navController: NavController, category: Category) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                when (category.name) {
                    "Berries" -> navController.navigate(Navigations.Route.BERRIES)
                }
            }, verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(category.url),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .padding(8.dp)
        )
        Text(text = category.name, modifier = Modifier.padding(8.dp))
    }
}


data class Category(val name: String, val url: String)