package org.skyfaced.network.model

import kotlinx.serialization.Serializable

@Serializable
data class LocationShortDto(
    val name: String,
    val url: String,
)