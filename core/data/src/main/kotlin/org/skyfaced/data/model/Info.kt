package org.skyfaced.data.model

import org.skyfaced.network.model.InfoDto

data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val previous: String?,
)

fun InfoDto.asModel() = Info(
    count = count,
    pages = pages,
    next = next,
    previous = previous,
)