package org.skyfaced.data.model

import org.skyfaced.network.model.CharacterDto

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Origin,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
)

fun CharacterDto.asModel() = Character(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = origin.asModel(),
    location = location.asModel(),
    image = image,
    episode = episode,
    url = url,
    created = created,
)