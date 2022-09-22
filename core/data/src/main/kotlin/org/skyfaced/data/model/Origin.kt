package org.skyfaced.data.model

import org.skyfaced.network.model.OriginDto

data class Origin(
    val name: String,
    val url: String,
)

fun OriginDto.asModel() = Origin(
    name = name,
    url = url
)