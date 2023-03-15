package com.dice.musicbrainz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
//        composable( // Screen for launching artist details
//
//        }
    }
}