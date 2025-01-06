package com.cabrera.manuel.trujillodi.base.navigation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.cabrera.manuel.trujillodi.base.Coordinator

class Navigation {

    private var _coordinators = mutableStateOf(value = listOf<Coordinator>())
    val coordinators: State<List<Coordinator>> = _coordinators

    fun add(coordinator: Coordinator) {
        val lastCoordinator = coordinators.value.lastOrNull()
        lastCoordinator?.pause()
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
        val lastCoordinator = coordinators.value.lastOrNull()
        lastCoordinator?.destroy()
        _coordinators.value = coordinators.value.toMutableList().apply {
            removeAt(coordinators.value.lastIndex)
        }
        val newLastCoordinator = coordinators.value.lastOrNull()
        newLastCoordinator?.resume()
    }

    fun popTo(coordinator: Coordinator) {
        _coordinators.value = coordinators.value.toMutableList().apply {
            subList(0, coordinators.value.indexOf(coordinator))
        }
    }
}