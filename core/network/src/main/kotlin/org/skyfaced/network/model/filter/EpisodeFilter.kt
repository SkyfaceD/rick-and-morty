package org.skyfaced.network.model.filter

data class EpisodeFilter(
    override val name: String? = null,
    val episode: String? = null
) : Filter

@Suppress("UNCHECKED_CAST")
fun EpisodeFilter.asMap() : Map<String, String> = hashMapOf(
    "name" to name,
    "episode" to episode,
).filterValues { it != null && it.isNotBlank() } as Map<String, String>