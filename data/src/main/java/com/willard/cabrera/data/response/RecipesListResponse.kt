package com.willard.cabrera.data.response

import kotlinx.serialization.Serializable

@Serializable
data class RecipesListResponse(
    val recipes: List<Recipe>
)