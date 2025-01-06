package com.cabrera.manuel.trujillodi.splash

import com.cabrera.manuel.trujillodi.base.Coordinator
import com.cabrera.manuel.trujillodi.base.EmitterData

class SplashCoordinatorFactory(
    private val parentCoordinator: Coordinator,
    private val emitterData: EmitterData,
) {

    fun create() = SplashCoordinator(
        parent = parentCoordinator,
        emitterData = emitterData,
    )
}