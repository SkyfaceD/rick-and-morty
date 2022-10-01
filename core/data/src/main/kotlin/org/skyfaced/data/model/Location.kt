package org.skyfaced.data.model

import org.skyfaced.network.model.LocationDto

data class Location(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<CharacterShort>,
)

fun LocationDto.asModel() = Location(
    id = id,
    name = name,
    type = type,
    dimension = if (dimension == "unknown") "" else dimension,
    residents = residents.map { CharacterShort(it.substringAfterLast('/').toInt()) }
)

