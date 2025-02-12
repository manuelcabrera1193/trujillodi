package com.willard.cabrera.data.repository

import com.willard.cabrera.data.mapper.RecipeMapper
import com.willard.cabrera.data.service.base.HttpClientProvider
import com.willard.cabrera.domain.model.RecipeModel
import com.willard.cabrera.domain.repository.RecipesRepository

class RecipesRepositoryImpl(private val httpClient: HttpClientProvider) : RecipesRepository {

    private val mapper: RecipeMapper by lazy {
        RecipeMapper()
    }

    override suspend fun getRecipes(): List<RecipeModel> {
        val response = httpClient.get("recipes")
            ?: return emptyList()

        return mapper.toModel(response)
    }
}