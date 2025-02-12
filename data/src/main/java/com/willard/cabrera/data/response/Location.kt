package com.willard.cabrera.data.response

import kotlinx.serialization.Serializable

@Serializable
data class Location (
    val latitude: String,
    val longitude: String,
)