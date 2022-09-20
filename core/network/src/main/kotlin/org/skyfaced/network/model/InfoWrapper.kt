package org.skyfaced.network.model

data class InfoWrapper<T>(
    val info: Info,
    val results: T,
)