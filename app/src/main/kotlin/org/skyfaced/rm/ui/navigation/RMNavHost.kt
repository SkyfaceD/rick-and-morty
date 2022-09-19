package org.skyfaced.rm.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import org.skyfaced.characters.CharactersNavigation
import org.skyfaced.characters.charactersGraph
import org.skyfaced.episodes.episodesGraph
import org.skyfaced.locations.locationsGraph
import org.skyfaced.navigation.RMNavigationDestination

@Composable
fun RMNavHost(
    navHostController: NavHostController,
    onNavigateToDestination: (RMNavigationDestination, String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: RMNavigationDestination = CharactersNavigation,
) = NavHost(
    navController = navHostController,
    startDestination = startDestination.route,
    modifier = modifier
) {
    charactersGraph()
    locationsGraph()
    episodesGraph()
}