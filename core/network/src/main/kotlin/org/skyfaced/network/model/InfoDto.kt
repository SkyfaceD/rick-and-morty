package org.skyfaced.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InfoDto(
    val count: Int,
    val pages: Int,
    val next: String?,
    @SerialName("prev")
    val previous: String?,
)