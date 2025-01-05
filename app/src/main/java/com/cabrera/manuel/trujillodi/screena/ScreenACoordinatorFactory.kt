package com.cabrera.manuel.trujillodi.screena

import com.cabrera.manuel.trujillodi.base.Coordinator
import com.cabrera.manuel.trujillodi.base.EmitterData
import kotlinx.coroutines.CoroutineScope

class ScreenACoordinatorFactory(
    private val scope: CoroutineScope,
    private val parentCoordinator: Coordinator,
    private val emitterData: EmitterData,
) {

    fun create() = ScreenACoordinator(
        scope = scope,
        parent = parentCoordinator,
        emitterData = emitterData,
    )
}