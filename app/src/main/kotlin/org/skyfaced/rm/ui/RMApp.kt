package org.skyfaced.rm.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import org.skyfaced.design.icon.Icon
import org.skyfaced.rm.ui.navigation.RMNavHost
import org.skyfaced.rm.ui.navigation.TopLevelDestination
import org.skyfaced.rm.ui.theme.RMTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun RMApp(
    windowSizeClass: WindowSizeClass,
    state: RMAppState = rememberRMAppState(windowSizeClass),
) {
    RMTheme(
        darkTheme = true,
        dynamicColor = true
    ) {
        Scaffold(
            bottomBar = {
                if (state.shouldShowBottomBar) {
                    RMBottomBar(
                        modifier = Modifier.windowInsetsPadding(
                            WindowInsets.safeDrawing.only(
                                WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom
                            )
                        ),
                        destinations = state.topLevelDestinations,
                        onNavigateToDestination = state::navigate,
                        currentDestination = state.currentDestination
                    )
                }
            }
        ) { innerPadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
            ) {
                if (state.shouldShowNavRail) {
                    RMNavRail(
                        modifier = Modifier.safeDrawingPadding(),
                        destinations = state.topLevelDestinations,
                        onNavigateToDestination = state::navigate,
                        currentDestination = state.currentDestination,
                    )
                }

                RMNavHost(
                    modifier = Modifier
                        .padding(innerPadding)
                        .consumedWindowInsets(innerPadding),
                    navHostController = state.navHostController,
                    onNavigateToDestination = state::navigate,
                    onBackClick = state::onBackClick,
                )
            }
        }
    }
}

@Composable
private fun RMBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) = NavigationBar(
    modifier = modifier
) {
    destinations.forEach { destination ->
        val selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true

        NavigationBarItem(
            selected = selected,
            onClick = { onNavigateToDestination(destination) },
            icon = {
                @Suppress("MoveVariableDeclarationIntoWhen")
                val icon = if (selected) destination.selectedIcon else destination.unselectedIcon
                when (icon) {
                    is Icon.DrawableResourceIcon -> Icon(
                        painter = painterResource(icon.id),
                        contentDescription = null
                    )
                    is Icon.ImageVectorIcon -> Icon(
                        imageVector = icon.imageVector,
                        contentDescription = null
                    )
                }
            },
            label = {
                Text(stringResource(destination.labelRes))
            },
            alwaysShowLabel = false,
        )
    }
}

@Composable
private fun RMNavRail(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) = NavigationRail(modifier = modifier) {
    destinations.forEach { destination ->
        val selected =
            currentDestination?.hierarchy?.any { it.route == destination.route } == true
        NavigationRailItem(
            selected = selected,
            onClick = { onNavigateToDestination(destination) },
            icon = {
                @Suppress("MoveVariableDeclarationIntoWhen")
                val icon = if (selected) destination.selectedIcon else destination.unselectedIcon
                when (icon) {
                    is Icon.DrawableResourceIcon -> Icon(
                        painter = painterResource(id = icon.id),
                        contentDescription = null
                    )
                    is Icon.ImageVectorIcon -> Icon(
                        imageVector = icon.imageVector,
                        contentDescription = null
                    )
                }
            },
            label = { Text(stringResource(destination.labelRes)) }
        )
    }
}