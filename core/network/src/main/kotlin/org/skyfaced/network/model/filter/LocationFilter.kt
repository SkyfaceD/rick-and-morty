package org.skyfaced.network.model.filter

data class LocationFilter(
    override val name: String? = null,
    val type: String? = null,
    val dimension: String? = null,
): Filter

@Suppress("UNCHECKED_CAST")
fun LocationFilter.asMap() : Map<String, String> = hashMapOf(
    "name" to name,
    "type" to type,
    "dimension" to dimension,
).filterValues { it != null && it.isNotBlank() } as Map<String, String>