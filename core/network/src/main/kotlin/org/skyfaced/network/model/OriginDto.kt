package org.skyfaced.network.model

import kotlinx.serialization.Serializable

@Serializable
data class OriginDto(
    val name: String,
    val url: String,
)