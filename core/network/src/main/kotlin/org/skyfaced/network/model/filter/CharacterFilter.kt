package org.skyfaced.network.model.filter

import org.skyfaced.network.model.filter.util.Gender
import org.skyfaced.network.model.filter.util.Status

data class CharacterFilter(
    override val name: String? = null,
    val status: Status? = null,
    val species: String? = null,
    val type: String? = null,
    val gender: Gender? = null
): Filter

@Suppress("UNCHECKED_CAST")
fun CharacterFilter.asMap() : Map<String, String> = hashMapOf(
    "name" to name,
    "status" to status?.name,
    "species" to species,
    "type" to type,
    "gender" to gender?.name,
).filterValues { it != null && it.isNotBlank() } as Map<String, String>
