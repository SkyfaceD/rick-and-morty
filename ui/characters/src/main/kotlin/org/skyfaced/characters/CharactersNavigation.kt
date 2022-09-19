package org.skyfaced.characters

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.skyfaced.navigation.RMNavigationDestination

object CharactersNavigation : RMNavigationDestination {
    override val route: String = "characters_route"
    override val destination: String = "characters_destination"
}

fun NavGraphBuilder.charactersGraph() {
    composable(
        route = CharactersNavigation.route,
    ) {
        CharactersRoute()
    }
}