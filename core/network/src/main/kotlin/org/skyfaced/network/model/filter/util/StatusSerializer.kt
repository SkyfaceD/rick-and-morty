package org.skyfaced.network.model.filter.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object StatusSerializer : KSerializer<Status> {
    override fun deserialize(decoder: Decoder): Status =
        decoder.decodeString().asStatus()

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "type",
        kind = PrimitiveKind.STRING
    )

    override fun serialize(encoder: Encoder, value: Status) = encoder.encodeString(value.name)
}