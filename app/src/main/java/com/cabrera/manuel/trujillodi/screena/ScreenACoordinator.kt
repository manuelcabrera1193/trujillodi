package com.cabrera.manuel.trujillodi.screena

import androidx.compose.ui.graphics.Color
import com.cabrera.manuel.trujillodi.base.Coordinator
import com.cabrera.manuel.trujillodi.base.EmitterData
import com.cabrera.manuel.trujillodi.base.Screen
import com.cabrera.manuel.trujillodi.base.ui.CustomState
import com.cabrera.manuel.trujillodi.util.getRandomComposeColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ScreenACoordinator(
    private val scope: CoroutineScope,
    private val parent: Coordinator,
    private val emitterData: EmitterData,
) : Coordinator, CustomState<ScreenUiStateA>(ScreenUiStateA()) {

    private val color: Color by lazy {
        getRandomComposeColor()
    }

    override val parentCoordinator: Coordinator
        get() = parent

    override val screen: Screen
        get() = ScreenA(
            state = state.value,
            events = ScreenUiEventsA(emitterData = emitterData),
        )

    override fun start() {
        println("ScreenACoordinator start")
        exampleEvent()
    }

    private fun exampleEvent() = scope.launch {
        delay(1000)
        updateState(state.value.copy(title = "Screen A 1"))
        delay(1000)
        updateState(state.value.copy(title = "Screen A 2"))
        delay(1000)
        updateState(state.value.copy(title = "Screen A 3"))
        delay(1000)
        updateState(state.value.copy(title = "Screen A 4"))
        delay(1000)
        updateState(state.value.copy(title = "Screen A 5"))
    }
}

