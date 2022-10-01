package org.skyfaced.data.model

import org.skyfaced.network.model.EpisodeDto

data class Episode(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<CharacterShort>,
)

fun EpisodeDto.asModel() = Episode(
    id = id,
    name = name,
    airDate = airDate,
    episode = episode,
    characters = characters.map { CharacterShort(it.substringAfterLast('/').toInt()) },
)
