package org.skyfaced.design.icon

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import org.skyfaced.design.R

object RMIcons {
    val CharactersSelected = R.drawable.ic_characters_selected
    val CharactersUnselected = R.drawable.ic_characters_unselected
    val LocationsSelected = R.drawable.ic_locations_selected
    val LocationsUnselected = R.drawable.ic_locations_unselected
    val EpisodesSelected = R.drawable.ic_episodes_selected
    val EpisodesUnselected = R.drawable.ic_episodes_unselected
}

sealed interface Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon

    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon
}