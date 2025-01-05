package com.cabrera.manuel.trujillodi.base

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class Navigation {

    private var _coordinators = mutableStateOf(value = listOf<Coordinator>())
    val coordinators: State<List<Coordinator>> = _coordinators

    fun add(coordinator: Coordinator) {
        println("Navigation add ${coordinator.screen.route}")
        _coordinators.value = coordinators.value.toMutableList().apply {
            add(coordinator)
            coordinator.start()
        }
        println("coordinators ${coordinators.value.joinToString("-") { it.screen.route }}")
    }

    fun replace(coordinator: Coordinator) {
        _coordinators.value = coordinators.value.toMutableList().apply {
            removeAt(coordinators.value.lastIndex)
            add(coordinator)
            coordinator.start()
        }
    }

    fun remove(coordinator: Coordinator) {
        _coordinators.value = coordinators.value.toMutableList().apply {
            remove(coordinator)
        }
    }

    fun pop() {
        _coordinators.value = coordinators.value.toMutableList().apply {
            removeAt(coordinators.value.lastIndex)
        }
    }

    fun popTo(coordinator: Coordinator) {
        _coordinators.value = coordinators.value.toMutableList().apply {
            subList(0, coordinators.value.indexOf(coordinator))
        }
    }
}