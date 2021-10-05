package com.sudzusama.pokemvi.ui.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.sudzusama.pokemvi.ui.feature.Navigations
import com.sudzusama.pokemvi.ui.feature.berries.BerriesScreen
import com.sudzusama.pokemvi.ui.feature.berry.BerryScreen
import com.sudzusama.pokemvi.ui.feature.categories.CategoriesScreen
import com.sudzusama.pokemvi.ui.theme.PokeMviTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeMviTheme {
                PokeApp()

            }
        }
    }
}

@ExperimentalCoilApi
@Preview
@Composable
fun PokeApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Navigations.Route.CATEGORIES) {
        composable(Navigations.Route.CATEGORIES) { CategoriesScreen(navController) }
        composable(Navigations.Route.BERRIES) { BerriesScreen(navController) }
        composable(
            Navigations.Route.BERRY_DETAILS,
            arguments = listOf(navArgument(Navigations.Arg.BERRY_NAME) {
                type = NavType.StringType
            })
        )
        { backStackEntry ->
            BerryScreen(
                navController,
                backStackEntry.arguments?.getString(Navigations.Arg.BERRY_NAME) ?: ""
            )
        }
    }
}