package org.skyfaced.network.model.filter.util

enum class Status {
    Alive,
    Dead,
    Unknown;
}

fun String?.asStatus() = when (this) {
    null -> Status.Unknown
    else -> Status.values().firstOrNull { type -> type.name == this } ?: Status.Unknown
}