package org.skyfaced.data.model

import org.skyfaced.data.util.AVATAR_EXTENSION
import org.skyfaced.data.util.AVATAR_PATH

data class CharacterShort(
    val id: Int,
    val image: String = AVATAR_PATH + id + AVATAR_EXTENSION,
)