package com.cabrera.manuel.trujillodi.screenb

import com.cabrera.manuel.trujillodi.base.EmitterData
import com.cabrera.manuel.trujillodi.base.Screen
import com.cabrera.manuel.trujillodi.base.coordinator.Coordinator
import com.cabrera.manuel.trujillodi.base.coordinator.active.ActiveState
import com.cabrera.manuel.trujillodi.base.navigation.NavigationEvents
import com.cabrera.manuel.trujillodi.base.navigation.NavigationService
import com.cabrera.manuel.trujillodi.base.ui.CustomState
import com.cabrera.manuel.trujillodi.ui.navigation.NavigationBarState
import com.cabrera.manuel.trujillodi.ui.toolbar.ToolbarState
import com.willard.cabrera.domain.model.RecipeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ScreenBCoordinator(
    private val scope: CoroutineScope,
    private val parent: Coordinator,
    private val emitterData: EmitterData,
    private val navigationService: NavigationService,
    recipe: RecipeModel,
) : Coordinator,
    CustomState<ScreenUiStateB>(ScreenUiStateB(recipe = recipe)),
    NavigationService by navigationService {

    private var time: Int = 1

    private fun counter() {
        println("ScreenBCoordinator fun counter")
        scope.launch {
            while (state.value.isActive) {
                println("ScreenBCoordinator counter")
                updateState(state.value.copy(title = "Screen B $time"))
                updateToolbarState(toolbar.copy(title = "Screen B $time"))
                delay(1000)
                time++
            }
        }
    }

    override val parentCoordinator: Coordinator
        get() = parent

    override val screen: Screen
        get() = ScreenB(
            state = state.value,
            emitterData = emitterData,
        )

    override fun start() {
        println("ScreenBCoordinator start")
        counter()
        updateNavigationBarState(NavigationBarState(visible = true))
        val toolbarState = ToolbarState(
            visible = true,
            title = state.value.title,
            showIconStart = true,
            eventStart = { emitterData.emitData(NavigationEvents.GO_TO_BACK) },
            showIconEnd = true,
            showMenu = true,
            eventEnd = { updateShowDrawer(true) },
        )
        updateToolbarState(toolbarState)
    }

    override fun resume() {
        updateState(state.value.copy(active = ActiveState.ENABLED))
        println("ScreenBCoordinator resume")
        println("ScreenBCoordinator state ${state.value.isActive}")
        counter()
        updateNavigationBarState(NavigationBarState(visible = true))
        val toolbarState = ToolbarState(
            visible = true,
            title = state.value.title,
            showIconStart = true,
            eventStart = { emitterData.emitData(NavigationEvents.GO_TO_BACK) },
            showIconEnd = true,
            showMenu = true,
            eventEnd = { updateShowDrawer(true) },
        )
        updateToolbarState(toolbarState)
    }

    override fun pause() {
        updateState(state.value.copy(active = ActiveState.DISABLED))
        println("ScreenBCoordinator pause")
        println("ScreenBCoordinator state ${state.value.isActive}")
    }

    override fun destroy() {
        updateState(state.value.copy(active = ActiveState.DISABLED))
        println("ScreenBCoordinator destroy")
        println("ScreenBCoordinator state ${state.value.isActive}")
    }
}

