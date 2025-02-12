package com.willard.cabrera.data.response

import kotlinx.serialization.Serializable

@Serializable
data class Recipe (
    val id: Long,
    val name: String,
    val image: String,
    val description: String,
    val usedIngredientCount: Long,
    val missedIngredientCount: Long,
    val likes: Long,
    val location: Location
)

