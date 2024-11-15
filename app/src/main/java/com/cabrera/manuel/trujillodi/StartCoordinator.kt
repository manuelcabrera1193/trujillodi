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

class StartCoordinator(override val navigation: Navigation, scope: CoroutineScope): Coordinator {

    private val emitterData: EmitterData = object : EmitterData {
        override fun emitData(event: Any) {
            when (event) {
                is ScreenAData -> {
                    val coordinator = screenBCoordinatorFactory.create()
                    navigation.add(coordinator)
                }
                is ScreenBData -> {
                    val coordinator = screenACoordinatorFactory.create()
                    navigation.add(coordinator)
                }
                else -> {

                }
            }
        }
    }


    private val screenACoordinatorFactory = ScreenACoordinatorFactory(
        scope = scope,
        parentCoordinator = this,
        emitterData = emitterData,
        navigation = navigation,
    )

    private val screenBCoordinatorFactory = ScreenBCoordinatorFactory(
        scope = scope,
        parentCoordinator = this,
        emitterData = emitterData,
        navigation = navigation,
    )

    override val parentCoordinator: Coordinator
        get() = this

    override val screen: Screen
        get() = screenACoordinatorFactory.create().screen
}