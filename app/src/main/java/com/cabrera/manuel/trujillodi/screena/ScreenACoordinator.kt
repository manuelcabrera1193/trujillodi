package com.cabrera.manuel.trujillodi.screena

import com.cabrera.manuel.trujillodi.base.EmitterData
import com.cabrera.manuel.trujillodi.base.Screen
import com.cabrera.manuel.trujillodi.base.coordinator.Coordinator
import com.cabrera.manuel.trujillodi.base.navigation.NavigationService
import com.cabrera.manuel.trujillodi.base.ui.CustomState
import com.cabrera.manuel.trujillodi.base.visibility.VisibilityState
import com.cabrera.manuel.trujillodi.ui.navigation.NavigationBarState
import com.cabrera.manuel.trujillodi.ui.toolbar.ToolbarState
import com.willard.cabrera.domain.model.RecipeModel
import com.willard.cabrera.domain.repository.RecipesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScreenACoordinator(
    private val scope: CoroutineScope,
    private val parent: Coordinator,
    private val emitterData: EmitterData,
    private val navigationService: NavigationService,
    private val repository: RecipesRepository,
) : Coordinator, CustomState<ScreenUiStateA>(ScreenUiStateA()),
    NavigationService by navigationService, EmitterData {

    override val parentCoordinator: Coordinator
        get() = parent

    override val screen: Screen
        get() = ScreenA(
            state = state.value,
            emitterData = this,
        )

    init {
        updateState(
            state.value.copy(
                goToDetail = ::goToDetail,
                retry = ::getRecipes,
                currentState = VisibilityState.LOADING,
            )
        )
    }

    private fun getRecipes() {
        println("Getting recipes")
        scope.launch(Dispatchers.IO) {
            try {
                val recipes: List<RecipeModel> = repository.getRecipes()
                updateState(
                    state.value.copy(
                        recipes = recipes,
                        currentState = VisibilityState.from(recipes),
                    )
                )
            } catch (throwable: Throwable) {
                updateState(state.value.copy(currentState = VisibilityState.ERROR))
            }
        }
    }

    private fun goToDetail(recipe: RecipeModel) {
        emitterData.emitData(data = recipe)
    }

    override fun start() {
        println("ScreenACoordinator start")
        updateToolbarState(ToolbarState())
        updateNavigationBarState(NavigationBarState())
        getRecipes()
    }

    override fun emitData(data: Any) {
        println("ScreenACoordinator emitData $data")
        emitterData.emitData(data)
    }

    override fun resume() {
        println("ScreenACoordinator resume")
        updateToolbarState(ToolbarState())
        updateNavigationBarState(NavigationBarState())
    }

    override fun pause() {
        println("ScreenACoordinator pause")
    }

    override fun destroy() {
        println("ScreenACoordinator destroy")
    }
}

