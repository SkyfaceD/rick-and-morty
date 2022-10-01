package org.skyfaced.data.model

import org.skyfaced.network.model.CharacterDto
import org.skyfaced.network.model.filter.util.Gender
import org.skyfaced.network.model.filter.util.Status

data class Character(
    val id: Int,
    val name: String,
    val status: Status,
    val species: String,
    val type: String,
    val gender: Gender,
    val originLocation: LocationShort?,
    val lastLocation: LocationShort?,
    val image: String,
    val episodes: List<Int>,
)

fun CharacterDto.asModel() = Character(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    originLocation = origin.asModelOrNull(),
    lastLocation = location.asModelOrNull(),
    image = image,
    episodes = episode.map { it.substringAfterLast('/').toInt() },
)