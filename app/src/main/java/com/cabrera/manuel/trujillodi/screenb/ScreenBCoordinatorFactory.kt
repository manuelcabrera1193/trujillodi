package com.cabrera.manuel.trujillodi.screenb

import com.cabrera.manuel.trujillodi.base.Coordinator
import com.cabrera.manuel.trujillodi.base.EmitterData
import com.cabrera.manuel.trujillodi.base.navigation.NavigationService
import kotlinx.coroutines.CoroutineScope

class ScreenBCoordinatorFactory(
    private val scope: CoroutineScope,
    private val parentCoordinator: Coordinator,
    private val emitterData: EmitterData,
    private val navigationService: NavigationService,
) {

    fun create() = ScreenBCoordinator(
        scope = scope,
        parent = parentCoordinator,
        emitterData = emitterData,
        navigationService = navigationService,
    )
}