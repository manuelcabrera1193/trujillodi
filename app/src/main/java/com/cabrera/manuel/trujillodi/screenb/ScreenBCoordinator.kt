package com.cabrera.manuel.trujillodi.screenb

import com.cabrera.manuel.trujillodi.base.Coordinator
import com.cabrera.manuel.trujillodi.base.EmitterData
import com.cabrera.manuel.trujillodi.base.Screen
import com.cabrera.manuel.trujillodi.base.ui.CustomState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ScreenBCoordinator(
    scope: CoroutineScope,
    private val parent: Coordinator,
    private val emitterData: EmitterData,
) : Coordinator, CustomState<ScreenUiStateB>(ScreenUiStateB()) {

    override val parentCoordinator: Coordinator
        get() = parent

    override val screen: Screen
        get() = ScreenB(
            state = state.value,
            emitterData = emitterData,
        )

    override fun start() {
        println("ScreenBCoordinator start")
    }

    init {
        scope.launch {
            var time = 1
            while (true) {
                updateState(state.value.copy(title = "Screen B $time"))
                delay(1000)
                time++
            }
        }
    }
}

