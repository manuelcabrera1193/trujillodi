package com.willard.cabrera.domain.model

data class RecipeModel(
    val id: Long,
    val name: String,
    val image: String,
    val description: String,
    val usedIngredientCount: Long,
    val missedIngredientCount: Long,
    val likes: Long,
    val location: LocationModel,
)

