package org.skyfaced.data.model

import org.skyfaced.network.model.LocationDto

data class Location(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<Int>,
)

fun LocationDto.asModel() = Location(
    id = id,
    name = name,
    type = type,
    dimension = dimension,
    residents = residents.map { it.substringAfterLast('/').toInt() },
)

