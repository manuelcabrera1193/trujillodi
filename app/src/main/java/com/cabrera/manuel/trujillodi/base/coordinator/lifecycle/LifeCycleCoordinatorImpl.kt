package com.cabrera.manuel.trujillodi.base.coordinator.lifecycle

import androidx.compose.runtime.State
import com.cabrera.manuel.trujillodi.base.coordinator.Coordinator

class LifeCycleCoordinatorImpl(
    private val lastCoordinator: State<Coordinator?>,
) : LifeCycleCoordinator {

    override fun resume() {
        super.resume()
        println("coordinator ${lastCoordinator.value}")
        lastCoordinator.value?.resume()
        println("LifeCycleCoordinatorImpl resume")
    }

    override fun pause() {
        super.pause()
        println("coordinator ${lastCoordinator.value}")
        lastCoordinator.value?.pause()
        println("LifeCycleCoordinatorImpl pause")
    }

    override fun stop() {
        super.pause()
        println("coordinator ${lastCoordinator.value}")
        lastCoordinator.value?.stop()
        println("LifeCycleCoordinatorImpl stop")
    }

    override fun destroy() {
        super.destroy()
        println("coordinator ${lastCoordinator.value}")
        lastCoordinator.value?.destroy()
        println("LifeCycleCoordinatorImpl destroy")
    }
}