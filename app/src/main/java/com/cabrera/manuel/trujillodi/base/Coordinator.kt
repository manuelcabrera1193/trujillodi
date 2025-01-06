package com.cabrera.manuel.trujillodi.base

import com.cabrera.manuel.trujillodi.base.lifecycle.LifeCycleView

interface Coordinator : LifeCycleView {
    val parentCoordinator: Coordinator
    val screen: Screen

    fun start()
    fun resume() {}
    fun pause() {}
    fun destroy() {}
}