package org.skyfaced.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InfoWrapper<T>(
    val info: Info,
    @SerialName("results")
    val data: T,
)