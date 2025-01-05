package com.cabrera.manuel.trujillodi

import com.cabrera.manuel.trujillodi.base.Coordinator
import com.cabrera.manuel.trujillodi.base.EmitterData
import com.cabrera.manuel.trujillodi.base.Navigation
import com.cabrera.manuel.trujillodi.base.Screen
import com.cabrera.manuel.trujillodi.screena.ScreenACoordinatorFactory
import com.cabrera.manuel.trujillodi.screena.ScreenAData
import com.cabrera.manuel.trujillodi.screenb.ScreenBCoordinatorFactory
import com.cabrera.manuel.trujillodi.screenb.ScreenBData
import kotlinx.coroutines.CoroutineScope

class StartCoordinator(
    val navigation: Navigation,
    scope: CoroutineScope
) : Coordinator {

    private val emitterData: EmitterData = object : EmitterData {
        override fun emitData(event: Any) {
            when (event) {
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

                }
            }
        }
    }

    private val screenACoordinatorFactory by lazy {
        ScreenACoordinatorFactory(
            scope = scope,
            parentCoordinator = this,
            emitterData = emitterData,
        )
    }

    private val screenBCoordinatorFactory  by lazy {
        ScreenBCoordinatorFactory(
            scope = scope,
            parentCoordinator = this,
            emitterData = emitterData,
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
        println("StartCoordinator end")
    }
}