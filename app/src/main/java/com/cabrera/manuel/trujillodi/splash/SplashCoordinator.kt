package com.cabrera.manuel.trujillodi.splash

import com.cabrera.manuel.trujillodi.base.Coordinator
import com.cabrera.manuel.trujillodi.base.EmitterData
import com.cabrera.manuel.trujillodi.base.Screen
import com.cabrera.manuel.trujillodi.base.ui.CustomState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashCoordinator(
    private val scope: CoroutineScope,
    private val parent: Coordinator,
    private val emitterData: EmitterData,
) : Coordinator, CustomState<SplashState>(SplashState()) {

    override val parentCoordinator: Coordinator
        get() = parent

    override val screen: Screen
        get() = Splash(
            state = state.value,
            emitterData = emitterData,
        )

    override fun start() {
        println("ScreenACoordinator start")
        scope.launch {
            delay(3000)
        }
    }
}

