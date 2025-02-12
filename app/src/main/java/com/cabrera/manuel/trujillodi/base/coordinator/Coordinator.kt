package com.cabrera.manuel.trujillodi.base.coordinator

import com.cabrera.manuel.trujillodi.base.Screen
import com.cabrera.manuel.trujillodi.base.coordinator.lifecycle.LifeCycleCoordinator

interface Coordinator : LifeCycleCoordinator {
    val parentCoordinator: Coordinator
    val screen: Screen
    fun start()
}