package org.skyfaced.network.model

import kotlinx.serialization.Serializable
import org.skyfaced.network.model.filter.util.Gender
import org.skyfaced.network.model.filter.util.GenderSerializer
import org.skyfaced.network.model.filter.util.Status
import org.skyfaced.network.model.filter.util.StatusSerializer

@Serializable
data class CharacterDto(
    val id: Int,
    val name: String,
    @Serializable(StatusSerializer::class)
    val status: Status,
    val species: String,
    val type: String,
    @Serializable(GenderSerializer::class)
    val gender: Gender,
    val origin: OriginDto,
    val location: OriginDto,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
)