package com.cabrera.manuel.trujillodi.screenb

import com.cabrera.manuel.trujillodi.base.coordinator.Coordinator
import com.cabrera.manuel.trujillodi.base.EmitterData
import com.cabrera.manuel.trujillodi.base.navigation.NavigationService
import com.willard.cabrera.domain.model.RecipeModel
import kotlinx.coroutines.CoroutineScope

class ScreenBCoordinatorFactory(
    private val scope: CoroutineScope,
    private val parentCoordinator: Coordinator,
    private val emitterData: EmitterData,
    private val navigationService: NavigationService,
) {

    fun create(recipe: RecipeModel) = ScreenBCoordinator(
        scope = scope,
        parent = parentCoordinator,
        emitterData = emitterData,
        navigationService = navigationService,
        recipe = recipe,
    )
}