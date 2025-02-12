package com.willard.cabrera.data.mapper

import com.willard.cabrera.data.response.Recipe
import com.willard.cabrera.domain.model.LocationModel
import com.willard.cabrera.domain.model.RecipeModel
import kotlinx.serialization.json.Json

class RecipeMapper {

    fun toModel(anyData: Any): List<RecipeModel> {

        println("anyData $anyData")

        val recipes: List<Recipe> = try {
            Json.decodeFromString(anyData.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }

        return recipes.map { recipe ->
            RecipeModel(
                id = recipe.id,
                name = recipe.name,
                image = recipe.image,
                description = recipe.description,
                usedIngredientCount = recipe.usedIngredientCount,
                missedIngredientCount = recipe.missedIngredientCount,
                likes = recipe.likes,
                location = LocationModel(
                    latitude = recipe.location.latitude,
                    longitude = recipe.location.longitude
                ),
            )
        }
    }
}