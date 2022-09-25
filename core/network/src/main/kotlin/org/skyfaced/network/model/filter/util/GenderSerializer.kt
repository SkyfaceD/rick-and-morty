package org.skyfaced.network.model.filter.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind.STRING
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object GenderSerializer : KSerializer<Gender> {
    override fun deserialize(decoder: Decoder): Gender =
        decoder.decodeString().asGender()

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "type",
        kind = STRING
    )

    override fun serialize(encoder: Encoder, value: Gender) = encoder.encodeString(value.name)
}