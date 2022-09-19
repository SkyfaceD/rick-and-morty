package org.skyfaced.rm.ui.navigation

import androidx.annotation.StringRes
import org.skyfaced.design.icon.Icon
import org.skyfaced.navigation.RMNavigationDestination

data class TopLevelDestination(
    override val route: String,
    override val destination: String,
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    @StringRes val labelRes: Int,
) : RMNavigationDestination