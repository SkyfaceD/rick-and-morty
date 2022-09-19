package org.skyfaced.episodes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.skyfaced.navigation.RMNavigationDestination

object EpisodesNavigation : RMNavigationDestination {
    override val route: String = "episodes_route"
    override val destination: String = "episodes_destination"
}

fun NavGraphBuilder.episodesGraph() {
    composable(
        route = EpisodesNavigation.route
    ) {
        EpisodesRoute()
    }
}