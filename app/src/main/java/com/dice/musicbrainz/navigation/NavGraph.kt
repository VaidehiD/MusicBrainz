package com.dice.musicbrainz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dice.feature.artist.details.ArtistDetailsScreen
import com.dice.feature.artist.search.SearchArtistsScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.SearchArtistsScreen.route
    ) {
        composable(route = Screen.SearchArtistsScreen.route) {
            SearchArtistsScreen(navController = navController)
        }
        composable(
            route = Screen.ArtistDetailsScreen.route,
            arguments = listOf(navArgument("artist_id") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            ArtistDetailsScreen(
                navController,
                backStackEntry.arguments?.getString("artist_id")
            )
        }
    }
}