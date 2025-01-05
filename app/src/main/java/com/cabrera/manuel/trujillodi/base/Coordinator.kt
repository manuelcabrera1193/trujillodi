package com.cabrera.manuel.trujillodi.base

interface Coordinator {
    val parentCoordinator: Coordinator
    val screen: Screen
    fun start()
}