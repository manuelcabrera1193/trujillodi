package com.cabrera.manuel.trujillodi.splash

import com.cabrera.manuel.trujillodi.base.Coordinator
import com.cabrera.manuel.trujillodi.base.EmitterData
import kotlinx.coroutines.CoroutineScope

class SplashCoordinatorFactory(
    private val scope: CoroutineScope,
    private val parentCoordinator: Coordinator,
    private val emitterData: EmitterData,
) {

    fun create() = SplashCoordinator(
        scope = scope,
        parent = parentCoordinator,
        emitterData = emitterData,
    )
}