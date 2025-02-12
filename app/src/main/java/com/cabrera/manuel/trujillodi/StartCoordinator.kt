package com.cabrera.manuel.trujillodi

import com.cabrera.manuel.trujillodi.base.coordinator.Coordinator
import com.cabrera.manuel.trujillodi.base.EmitterData
import com.cabrera.manuel.trujillodi.base.Screen
import com.cabrera.manuel.trujillodi.base.navigation.Navigation
import com.cabrera.manuel.trujillodi.base.navigation.NavigationService
import com.cabrera.manuel.trujillodi.screena.ScreenACoordinatorFactory
import com.cabrera.manuel.trujillodi.screenb.ScreenBCoordinatorFactory
import com.cabrera.manuel.trujillodi.screenb.ScreenBData
import com.willard.cabrera.data.service.base.HttpClientFactory
import com.willard.cabrera.domain.model.RecipeModel
import kotlinx.coroutines.CoroutineScope

class StartCoordinator(
    private val navigation: Navigation,
    private val scope: CoroutineScope,
    private val emitterData: EmitterData,
    private val navigationService: NavigationService,
    private val httpClientFactory: HttpClientFactory,
) : Coordinator, EmitterData {

    private val screenACoordinatorFactory by lazy {
        ScreenACoordinatorFactory(
            scope = scope,
            parentCoordinator = this,
            emitterData = this,
            navigationService = navigationService,
            httpClientFactory = httpClientFactory,
        )
    }

    private val screenBCoordinatorFactory by lazy {
        ScreenBCoordinatorFactory(
            scope = scope,
            parentCoordinator = this,
            emitterData = this,
            navigationService = navigationService,
        )
    }

    override val parentCoordinator: Coordinator by lazy {
        this
    }

    override val screen: Screen
        get() = navigation.coordinators.value.last().screen

    override fun start() {
        println("StartCoordinator start")
        navigation.replace(screenACoordinatorFactory.create())
    }

    override fun emitData(data: Any) {
        when (data) {
            is RecipeModel -> {
                val coordinator = screenBCoordinatorFactory.create(recipe = data)
                navigation.add(coordinator)
            }

            is ScreenBData -> {
                println("ScreenBData")
                val coordinator = screenACoordinatorFactory.create()
                navigation.add(coordinator)
            }

            else -> {
                emitterData.emitData(data)
            }
        }
    }
}