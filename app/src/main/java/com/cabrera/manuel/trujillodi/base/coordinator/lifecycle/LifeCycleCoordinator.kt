package com.cabrera.manuel.trujillodi.base.coordinator.lifecycle

interface LifeCycleCoordinator {
    fun resume() {}
    fun pause() {}
    fun stop() {}
    fun destroy() {}
}