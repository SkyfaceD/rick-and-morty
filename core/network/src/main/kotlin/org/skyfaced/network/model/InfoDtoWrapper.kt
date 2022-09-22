package org.skyfaced.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InfoDtoWrapper<T>(
    val info: InfoDto,
    @SerialName("results")
    val data: List<T>,
)