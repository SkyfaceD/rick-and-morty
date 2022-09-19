package org.skyfaced.locations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.skyfaced.navigation.RMNavigationDestination

object LocationsNavigation : RMNavigationDestination {
    override val route: String = "locations_route"
    override val destination: String = "locations_destination"
}

fun NavGraphBuilder.locationsGraph() {
    composable(
        route = LocationsNavigation.route
    ) {
        LocationsRoute()
    }
}