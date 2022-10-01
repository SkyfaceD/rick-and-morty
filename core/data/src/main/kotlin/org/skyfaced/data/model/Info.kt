package org.skyfaced.data.model

import org.skyfaced.network.model.InfoDto

data class Info(
    val count: Int,
    val pages: Int,
    val next: Int?,
    val previous: Int?,
)

fun InfoDto.asModel() = Info(
    count = count,
    pages = pages,
    next = next?.substringAfterLast('=')?.toIntOrNull(),
    previous = previous?.substringAfterLast('=')?.toIntOrNull(),
)