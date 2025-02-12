package com.willard.cabrera.domain.repository

import com.willard.cabrera.domain.model.RecipeModel

interface RecipesRepository {

    suspend fun getRecipes(): List<RecipeModel>
}