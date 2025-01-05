package com.cabrera.manuel.trujillodi

import com.cabrera.manuel.trujillodi.base.Coordinator
import com.cabrera.manuel.trujillodi.base.EmitterData
import com.cabrera.manuel.trujillodi.base.Screen
import com.cabrera.manuel.trujillodi.base.navigation.Navigation
import com.cabrera.manuel.trujillodi.screena.ScreenACoordinatorFactory
import com.cabrera.manuel.trujillodi.screena.ScreenAData
import com.cabrera.manuel.trujillodi.screenb.ScreenBCoordinatorFactory
import com.cabrera.manuel.trujillodi.screenb.ScreenBData
import kotlinx.coroutines.CoroutineScope

class StartCoordinator(
    private val navigation: Navigation,
    private val scope: CoroutineScope,
    private val emitterData: EmitterData,
) : Coordinator, EmitterData {

    private val screenACoordinatorFactory by lazy {
        ScreenACoordinatorFactory(
            scope = scope,
            parentCoordinator = this,
            emitterData = this,
        )
    }

    private val screenBCoordinatorFactory by lazy {
        ScreenBCoordinatorFactory(
            scope = scope,
            parentCoordinator = this,
            emitterData = this,
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
            is ScreenAData -> {
                println("ScreenAData")
                val coordinator = screenBCoordinatorFactory.create()
                navigation.add(coordinator)
                coordinator.start()
            }

            is ScreenBData -> {
                println("ScreenBData")
                val coordinator = screenACoordinatorFactory.create()
                navigation.add(coordinator)
                coordinator.start()
            }

            else -> {
                emitterData.emitData(data)
            }
        }
    }
}