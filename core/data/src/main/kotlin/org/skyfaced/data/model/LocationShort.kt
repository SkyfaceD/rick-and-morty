package org.skyfaced.data.model

import org.skyfaced.network.model.LocationShortDto

data class LocationShort(
    val id: Int,
    val name: String,
)

fun LocationShortDto.asModelOrNull(): LocationShort? {
    if (url.isEmpty()) return null

    return LocationShort(
        id = url.substringAfterLast('/').toInt(),
        name = name,
    )
}