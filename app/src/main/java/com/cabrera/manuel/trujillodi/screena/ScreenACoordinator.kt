package com.cabrera.manuel.trujillodi.screena

import com.cabrera.manuel.trujillodi.base.Coordinator
import com.cabrera.manuel.trujillodi.base.EmitterData
import com.cabrera.manuel.trujillodi.base.Screen
import com.cabrera.manuel.trujillodi.base.navigation.NavigationService
import com.cabrera.manuel.trujillodi.base.ui.CustomState
import com.cabrera.manuel.trujillodi.ui.navigation.NavigationBarState
import com.cabrera.manuel.trujillodi.ui.toolbar.ToolbarState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ScreenACoordinator(
    private val scope: CoroutineScope,
    private val parent: Coordinator,
    private val emitterData: EmitterData,
    private val navigationService: NavigationService,
) : Coordinator, CustomState<ScreenUiStateA>(ScreenUiStateA()),
    NavigationService by navigationService, EmitterData {

    private var time: Int = 0
    private var isActive: Boolean = false

    private fun counter() {
        scope.launch {
            while (isActive) {
                updateState(state.value.copy(title = "Screen A $time"))
                delay(2000)
                time+=2
            }
        }
    }

    override val parentCoordinator: Coordinator
        get() = parent

    override val screen: Screen
        get() = ScreenA(
            state = state.value,
            emitterData = this,
        )

    override fun start() {
        println("ScreenACoordinator start")
        isActive = true
        counter()
        updateToolbarState(ToolbarState())
        updateNavigationBarState(NavigationBarState())
    }

    override fun emitData(data: Any) {
        println("ScreenACoordinator emitData $data")
        emitterData.emitData(data)
    }

    override fun resume() {
        println("ScreenACoordinator resume")
        isActive = true
        counter()
        updateToolbarState(ToolbarState())
        updateNavigationBarState(NavigationBarState())
    }

    override fun pause() {
        println("ScreenACoordinator pause")
        isActive = false
    }

    override fun destroy() {
        println("ScreenACoordinator destroy")
        isActive = false
    }
}

