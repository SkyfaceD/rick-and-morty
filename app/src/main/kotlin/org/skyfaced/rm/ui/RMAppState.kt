package org.skyfaced.rm.ui

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.skyfaced.characters.CharactersNavigation
import org.skyfaced.design.icon.Icon
import org.skyfaced.design.icon.RMIcons
import org.skyfaced.episodes.EpisodesNavigation
import org.skyfaced.locations.LocationsNavigation
import org.skyfaced.navigation.RMNavigationDestination
import org.skyfaced.rm.R
import org.skyfaced.rm.ui.navigation.TopLevelDestination

@Stable
class RMAppState(
    val navHostController: NavHostController,
    val windowSizeClass: WindowSizeClass,
) {
    val currentDestination: NavDestination?
        @Composable
        get() = navHostController.currentBackStackEntryAsState().value?.destination

    val shouldShowBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact ||
                windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact

    val shouldShowNavRail: Boolean
        get() = !shouldShowBottomBar

    /**
     * Top level destinations to be used in the BottomBar and NavRail
     */
    val topLevelDestinations: List<TopLevelDestination> = listOf(
        TopLevelDestination(
            route = CharactersNavigation.route,
            destination = CharactersNavigation.destination,
            selectedIcon = Icon.DrawableResourceIcon(RMIcons.CharactersSelected),
            unselectedIcon = Icon.DrawableResourceIcon(RMIcons.CharactersUnselected),
            labelRes = R.string.lbl_characters
        ),
        TopLevelDestination(
            route = LocationsNavigation.route,
            destination = LocationsNavigation.destination,
            selectedIcon = Icon.DrawableResourceIcon(RMIcons.LocationsSelected),
            unselectedIcon = Icon.DrawableResourceIcon(RMIcons.LocationsUnselected),
            labelRes = R.string.lbl_locations
        ),
        TopLevelDestination(
            route = EpisodesNavigation.route,
            destination = EpisodesNavigation.destination,
            selectedIcon = Icon.DrawableResourceIcon(RMIcons.EpisodesSelected),
            unselectedIcon = Icon.DrawableResourceIcon(RMIcons.EpisodesUnselected),
            labelRes = R.string.lbl_episodes
        )
    )

    fun navigate(destination: RMNavigationDestination, route: String? = null) {
        if (destination is TopLevelDestination) {
            navHostController.navigate(route ?: destination.route) {
                popUpTo(navHostController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        } else {
            navHostController.navigate(route ?: destination.route)
        }
    }

    fun onBackClick() {
        navHostController.popBackStack()
    }
}

@Composable
fun rememberRMAppState(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController = rememberNavController(),
): RMAppState = remember(navController, windowSizeClass) {
    RMAppState(navController, windowSizeClass)
}