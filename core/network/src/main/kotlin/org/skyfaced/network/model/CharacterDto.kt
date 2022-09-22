package org.skyfaced.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: OriginDto,
    val location: OriginDto,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
)