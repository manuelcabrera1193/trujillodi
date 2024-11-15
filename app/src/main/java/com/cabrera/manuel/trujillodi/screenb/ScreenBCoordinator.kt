package com.cabrera.manuel.trujillodi.screenb

import com.cabrera.manuel.trujillodi.base.Coordinator
import com.cabrera.manuel.trujillodi.base.EmitterData
import com.cabrera.manuel.trujillodi.base.Navigation
import com.cabrera.manuel.trujillodi.base.Screen
import com.cabrera.manuel.trujillodi.base.ui.CustomState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ScreenBCoordinator(
    scope: CoroutineScope,
    private val parent: Coordinator,
    private val emitterData: EmitterData,
    override val navigation: Navigation,
) : Coordinator, CustomState<ScreenUiStateB>(ScreenUiStateB()) {

    override val parentCoordinator: Coordinator
        get() = parent

    override val screen: Screen
        get() = ScreenB(
            state = state.value,
            events = ScreenUiEventsB(emitterData),
        )

    init {
        scope.launch {
            var time = 1
            while (true) {
                updateState(state.value.copy(title = "Screen A $time"))
                delay(1000)
                time++
            }
        }
    }
}

