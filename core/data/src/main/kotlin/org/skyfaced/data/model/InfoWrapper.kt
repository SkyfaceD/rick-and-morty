package org.skyfaced.data.model

data class InfoWrapper<T>(
    val info: Info,
    val data: List<T>,
)

infix fun <T> Info.wrap(that: List<T>): InfoWrapper<T> = InfoWrapper(this, that)

infix fun <T> List<T>.wrap(that: Info): InfoWrapper<T> = InfoWrapper(that, this)