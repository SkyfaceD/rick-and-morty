package org.skyfaced.network.model.filter.util

enum class Gender {
    Female,
    Male,
    Genderless,
    Unknown;
}

fun String?.asGender() = when (this) {
    null -> Gender.Unknown
    else -> Gender.values().firstOrNull { type -> type.name == this } ?: Gender.Unknown
}