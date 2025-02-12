package com.cabrera.manuel.trujillodi.splash

import com.cabrera.manuel.trujillodi.base.coordinator.Coordinator
import com.cabrera.manuel.trujillodi.base.EmitterData
import com.cabrera.manuel.trujillodi.base.Screen
import com.cabrera.manuel.trujillodi.base.ui.CustomState

class SplashCoordinator(
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
    }
}

